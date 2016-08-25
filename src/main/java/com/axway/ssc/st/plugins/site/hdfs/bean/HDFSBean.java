package com.axway.ssc.st.plugins.site.hdfs.bean;

import javax.validation.constraints.Pattern;

import com.axway.st.plugins.site.UIBean;

public class HDFSBean implements UIBean {

	/** Holds the HDFS Host Name. */
	@Pattern(regexp = "\\S*", message = "HDFS Host Name could not contain whitespaces.")
	private String mHdfsHostName;

	private String mAutoCreateDirectory;

	/** Holds the HDFS port. */
	private String mHdfsPort;

	/** Holds the optional HDFS Url */
	private String mHdfsUrl;

	private String mHdfsUploadFolder;
	
	private String mHdfsDownloadFolder;
	
	private String mHdfsDownloadObjectKey;

	/** Holds the network zone name. */
	private String mNetworkZone;

	/**
	 * @return the mHdfsHostName
	 */
	public String getHdfsHostName() {
		return mHdfsHostName;
	}

	/**
	 * @param mHdfsHostName the mHdfsHostName to set
	 */
	public void setHdfsHostName(String mHdfsHostName) {
		this.mHdfsHostName = mHdfsHostName;
	}

	/**
	 * @return the mAutoCreateDirectory
	 */
	public String getAutoCreateDirectory() {
		return mAutoCreateDirectory;
	}

	/**
	 * @param mAutoCreateDirectory the mAutoCreateDirectory to set
	 */
	public void setAutoCreateDirectory(String mAutoCreateDirectory) {
		this.mAutoCreateDirectory = mAutoCreateDirectory;
	}

	/**
	 * @return the mHdfsPort
	 */
	public String getHdfsPort() {
		return mHdfsPort;
	}

	/**
	 * @param mHdfsPort the mHdfsPort to set
	 */
	public void setHdfsPort(String mHdfsPort) {
		this.mHdfsPort = mHdfsPort;
	}

	/**
	 * @return the mHdfsUrl
	 */
	public String getHdfsUrl() {
		return mHdfsUrl;
	}

	/**
	 * @param mHdfsUrl the mHdfsUrl to set
	 */
	public void setHdfsUrl(String mHdfsUrl) {
		this.mHdfsUrl = mHdfsUrl;
	}

	/**
	 * @return the mHdfsUploadFolder
	 */
	public String getHdfsUploadFolder() {
		return mHdfsUploadFolder;
	}

	/**
	 * @param mHdfsUploadFolder the mHdfsUploadFolder to set
	 */
	public void setHdfsUploadFolder(String mHdfsUploadFolder) {
		this.mHdfsUploadFolder = mHdfsUploadFolder;
	}

	/**
	 * @return the mHdfsDownloadFolder
	 */
	public String getHdfsDownloadFolder() {
		return mHdfsDownloadFolder;
	}

	/**
	 * @param mHdfsDownloadFolder the mHdfsDownloadFolder to set
	 */
	public void setHdfsDownloadFolder(String mHdfsDownloadFolder) {
		this.mHdfsDownloadFolder = mHdfsDownloadFolder;
	}

	/**
	 * @return the mHdfsDownloadObjectKey
	 */
	public String getHdfsDownloadObjectKey() {
		return mHdfsDownloadObjectKey;
	}

	/**
	 * @param mHdfsDownloadObjectKey the mHdfsDownloadObjectKey to set
	 */
	public void setHdfsDownloadObjectKey(String mHdfsDownloadObjectKey) {
		this.mHdfsDownloadObjectKey = mHdfsDownloadObjectKey;
	}

	/**
	 * @return the mNetworkZone
	 */
	public String getNetworkZone() {
		return mNetworkZone;
	}

	/**
	 * @param mNetworkZone the mNetworkZone to set
	 */
	public void setNetworkZone(String mNetworkZone) {
		this.mNetworkZone = mNetworkZone;
	}

}
