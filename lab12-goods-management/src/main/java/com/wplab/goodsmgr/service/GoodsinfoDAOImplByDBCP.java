package com.wplab.goodsmgr.service;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GoodsinfoDAOImplByDBCP extends GoodsinfoDAOImpl {
	
	private String dbcpResourceName;
	private DataSource ds;
	
	public GoodsinfoDAOImplByDBCP() {
	}
	
	public GoodsinfoDAOImplByDBCP(String dbcpResourceName) {
		setDbcpResourceName(dbcpResourceName);
	}

	public void setDbcpResourceName(String dbcpResourceName) {
		this.dbcpResourceName = dbcpResourceName;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup(dbcpResourceName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void connect() throws SQLException {
		if (ds != null)
			this.conn = ds.getConnection();
	}
	
}
