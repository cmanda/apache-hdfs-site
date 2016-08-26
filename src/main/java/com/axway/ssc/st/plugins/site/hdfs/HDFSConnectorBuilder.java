/**
 * 
 */
package com.axway.ssc.st.plugins.site.hdfs;

import com.axway.ssc.st.plugins.site.hdfs.bean.HDFSBean;

public class HDFSConnectorBuilder {

	/**
	 * Creates and configures HDFSConnector from the given bean
	 * 
	 */
	public AbstractHDFSConnector build(HDFSBean config) {
		AbstractHDFSConnector hdfsConnector = null;

		hdfsConnector = new HDFSConnectorImpl(config);

		return hdfsConnector;
	}

}
