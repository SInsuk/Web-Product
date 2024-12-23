package com.wplab.service;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminDAOImplByDBCP extends AdminDAOImpl {
    
    private String dbcpResourceName;
    private DataSource ds;
    
    public AdminDAOImplByDBCP() {
    }
    
    public AdminDAOImplByDBCP(String dbcpResourceName) {
        setDbcpResourceName(dbcpResourceName);
    }

    public void setDbcpResourceName(String dbcpResourceName) {
        this.dbcpResourceName = dbcpResourceName;
        
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup(dbcpResourceName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void connect() throws SQLException {
        if (ds != null)
            this.conn = ds.getConnection();
    }
    
}
