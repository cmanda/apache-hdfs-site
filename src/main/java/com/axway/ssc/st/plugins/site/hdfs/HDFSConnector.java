package com.axway.ssc.st.plugins.site.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.hadoop.hdfs.DFSClient;

import com.axway.st.plugins.site.TransferFailedException;

/**
 * 
 * @author cmanda
 *
 */
interface HDFSConnector {

	/**
	 * Connects to a remote site.
	 * 
	 * @return a connection configuration object from Hadoop HDFS.
	 * @throws TransferFailedException
	 *             on connection failure.
	 */
	public DFSClient connect() throws TransferFailedException;

	/**
	 * Disconnects from a remote site.
	 */
	public void disconnect();

	/**
	 * Downloads a file.
	 * 
	 * @param outputStream
	 *            the output
	 * @param downloadFolder
	 *            the Bucket name
	 * @param downloadObjectName
	 *            the remote object for download
	 * @throws IOException
	 *             on error
	 */
	public void download(OutputStream outputStream, String downloadFolder, String downloadObjectName) throws IOException;

	/**
	 * Uploads a file.
	 * 
	 * @param inputStream
	 *            the input
	 * @param uploadFolder
	 *            the remote parent
	 * @param uploadObjectName
	 *            the remote file name.
	 * @throws IOException
	 *             on error
	 */
	public void upload(InputStream inputStream, String uploadFolder, String uploadObjectName, long objectSize)
			throws IOException;

	/**
	 * List files from a remote directory, matching the pattern.
	 * 
	 * @param remoteDir
	 *            the remote directory to list
	 * @param remotePattern
	 *            the pattern
	 * @return the list of files
	 * @throws IOException
	 *             on error
	 */
	public List<String> listFiles(String remoteDir, String remotePattern) throws IOException;

}
