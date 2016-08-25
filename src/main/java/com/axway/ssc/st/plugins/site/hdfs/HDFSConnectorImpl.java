package com.axway.ssc.st.plugins.site.hdfs;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.hdfs.DFSClient;
import org.apache.log4j.Logger;

import com.axway.ssc.st.plugins.site.hdfs.bean.HDFSBean;
import com.axway.st.plugins.site.TransferFailedException;

public class HDFSConnectorImpl extends AbstractHDFSConnector {

	private static final Logger logger = Logger.getLogger(HDFSConnectorImpl.class);

	private DFSClient mDfsClient;

	private static final String LOGGER_KEY = "[HDFSConnectorImpl]: ";
	private static final String HDFS_DEFAULTFS_KEY = "fs.defaultFS";

	public HDFSConnectorImpl(HDFSBean config) {
		super(config);
	}

	@Override
	public DFSClient connect() throws TransferFailedException {

		Configuration hdfsConfig = new Configuration(true);
		String hdfsUrl = checkNotNull(getConfiguration().getHdfsUrl());
		String hdfsHostName = checkNotNull(getConfiguration().getHdfsHostName());
		String hdfsPort = checkNotNull(getConfiguration().getHdfsPort());

		if (hdfsUrl.isEmpty() || null == hdfsUrl) {
			hdfsUrl = "hdfs://" + hdfsHostName + ":" + hdfsPort;
		}
		hdfsConfig.set(HDFS_DEFAULTFS_KEY, hdfsUrl);
		logger.debug(LOGGER_KEY + "HDFS Connection parameters set");
		try {
			mDfsClient = new DFSClient(new URI(hdfsUrl), hdfsConfig);
		} catch (IOException ioe) {
			logger.error(LOGGER_KEY + ioe.getMessage());
		} catch (URISyntaxException urise) {
			logger.error(LOGGER_KEY + urise.getMessage());
		}

		return mDfsClient;

	}

	@Override
	public void disconnect() {
		if (mDfsClient != null)
			try {
				mDfsClient.close();
			} catch (IOException e) {
				logger.error(LOGGER_KEY + "Error closing DFS connection: " + e.getMessage());
			}

	}

	@Override
	public void download(OutputStream outputStream, String awsBucketName, String downloadObjectName)
			throws IOException {
		logger.info(LOGGER_KEY + "Download functionality not yet written.");

	}

	@Override
	public void upload(InputStream inputStream, String awsBucketName, String uploadObjectName, long objectSize) {
		String autoCreateDirectory = checkNotNull(getConfiguration().getAutoCreateDirectory());
		String uploadDirectory = checkNotNull(getConfiguration().getHdfsUploadFolder());
		InputStream uploadStream = new AlwaysOpenOutputStream(inputStream);
		String uploadPath = uploadDirectory + "/" + uploadObjectName;
		OutputStream targetStream = null;
		try {
			logger.debug(LOGGER_KEY + "Upload Folder: " + uploadDirectory);
			if (mDfsClient.exists(uploadDirectory)) {
				logger.debug(LOGGER_KEY + "Upload directory exists.");
			} else if (autoCreateDirectory.equalsIgnoreCase("true")) {
				logger.debug(LOGGER_KEY + "Auto Creating upload directory");
				logger.info(LOGGER_KEY + "Autocreating directory structure: "
						+ mDfsClient.mkdirs(uploadDirectory, FsPermission.getDefault(), true));
			} else {
				logger.info(LOGGER_KEY + " Auto creating disabled. Unable to find the directory: " + uploadDirectory);
			}

			if (mDfsClient.exists(uploadPath)) {
				logger.info(LOGGER_KEY
						+ "File exists. Overwriting by default - menu option to be provided in the future to detect collison settings.");
			}
			targetStream = mDfsClient.create(uploadPath, true);
			logger.debug(LOGGER_KEY + "Resource created on the target system [hdfs] at: " + uploadPath
					+ " with overwrite flag ON");
			long response = IOUtils.copy(uploadStream, targetStream);
			logger.debug(LOGGER_KEY + "IOUtils Stream copy task finished: " + response);
			uploadStream.close();
			logger.debug(LOGGER_KEY + "Input stream closed");
			targetStream.close();
			logger.debug(LOGGER_KEY + "Target stream closed");
		} catch (IOException ioe) {
			logger.error(LOGGER_KEY + "IO Exception occurred: " + ioe.getMessage());
		} catch (Exception e) {
			logger.error(LOGGER_KEY + "Exception: " + e.getMessage());
		} 

	}

	@Override
	public List<String> listFiles(String remoteDir, String remotePattern) throws IOException {
		return new ArrayList<String>();
	}

	@Override
	protected boolean setProxySettings(String zone) {
		return false;
	}

}
