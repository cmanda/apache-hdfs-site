/**
 * 
 */
package com.axway.ssc.st.plugins.site.hdfs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hdfs.DFSClient;

import com.axway.ssc.st.plugins.site.hdfs.bean.HDFSBean;
import com.axway.st.plugins.site.CustomSite;
import com.axway.st.plugins.site.DestinationFile;
import com.axway.st.plugins.site.FileItem;
import com.axway.st.plugins.site.RemotePartner;
import com.axway.st.plugins.site.SourceFile;
import com.axway.st.plugins.site.TransferFailedException;
import com.axway.st.plugins.site.services.CertificateService;
import com.axway.st.plugins.site.services.ProxyService;

/**
 * @author cmanda
 *
 */
public class HDFSSite extends CustomSite {

	/**
	 * UIBean implementation
	 */
	private HDFSBean mHdfsBean = new HDFSBean();
	
	/**
	 * HDFS Client implementation. Allows connect/disconnect/upload/download/list functionalities
	 */
	private AbstractHDFSConnector mHdfsConnection;
	
	/**
	 * Provides Proxy Server Configuration
	 */
	@Inject
	private ProxyService mProxyService;
	
	/**
	 * Used to create SSLContext, configured with private key, to authenticate
	 * with.
	 */
	@SuppressWarnings("unused")
	@Inject
	private CertificateService mCertificateService;
	
	public HDFSSite() {
		setUIBean(mHdfsBean);

	}

	/* (non-Javadoc)
	 * @see com.axway.st.plugins.site.Connection#finalizeExecution()
	 */
	@Override
	public void finalizeExecution() throws IOException {
		mHdfsConnection = null;

	}

	/* (non-Javadoc)
	 * @see com.axway.st.plugins.site.Connection#getFile(com.axway.st.plugins.site.DestinationFile)
	 */
	@Override
	public void getFile(DestinationFile destFile) throws IOException {
		DFSClient dfsClient = null;
		if (mHdfsConnection == null) {
			dfsClient = connect();
		}
		mHdfsConnection.download(
				destFile.getOutputStream(
						new RemotePartner(dfsClient.getCanonicalServiceName(), mHdfsBean.getHdfsDownloadFolder())),
				mHdfsBean.getHdfsDownloadFolder(), mHdfsBean.getHdfsDownloadObjectKey());

	}

	/* (non-Javadoc)
	 * @see com.axway.st.plugins.site.Connection#list()
	 */
	@Override
	public List<FileItem> list() throws IOException {
		if (mHdfsConnection == null) {
			connect();
		}

		List<String> names = mHdfsConnection.listFiles(mHdfsBean.getHdfsDownloadFolder(), mHdfsBean.getHdfsDownloadFolder());
		List<FileItem> result = new ArrayList<FileItem>();
		if (names != null && names.size() > 0) {
			for (String name : names) {
				result.add(new FileItem(name));
			}
		}
		return result;
	}

	public DFSClient connect() throws TransferFailedException {
		mHdfsConnection = new HDFSConnectorBuilder().build(mHdfsBean);
		mHdfsConnection.setProxyService(mProxyService);
		return mHdfsConnection.connect();
		
	}

	/* (non-Javadoc)
	 * @see com.axway.st.plugins.site.Connection#putFile(com.axway.st.plugins.site.SourceFile)
	 */
	@Override
	public void putFile(SourceFile srcFile) throws IOException {
		DFSClient dfsClient = null;
		if(null == mHdfsConnection) {
			dfsClient = connect();
		}
		mHdfsConnection.upload(
				srcFile.getInputStream(
						new RemotePartner(dfsClient.getCanonicalServiceName(), mHdfsBean.getHdfsUploadFolder())),
				mHdfsBean.getHdfsUploadFolder(), srcFile.getName(), srcFile.getSize());

	}

}
