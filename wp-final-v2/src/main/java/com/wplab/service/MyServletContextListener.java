package com.wplab.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyServletContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println(">>> ServletContext object initialized...");
         
         DBConnectionInfo dbInfo = new DBConnectionInfo();
         dbInfo.setDriverClassName(sce.getServletContext().getInitParameter("jdbc.driverClassName"));
         dbInfo.setUrl(sce.getServletContext().getInitParameter("jdbc.url"));
         dbInfo.setUsername(sce.getServletContext().getInitParameter("jdbc.username"));
         dbInfo.setPassword(sce.getServletContext().getInitParameter("jdbc.password"));

         System.out.println("DBConnectionInfo: " + dbInfo);
         sce.getServletContext().setAttribute("dbInfo", dbInfo);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        System.out.println(">>> ServletContext object destroyed...");
    }
	
}
