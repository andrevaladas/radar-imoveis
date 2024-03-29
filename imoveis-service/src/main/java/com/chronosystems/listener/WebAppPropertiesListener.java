/**
 * 
 */
package com.chronosystems.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Define variavel para conexão ao banco de dados HSQLBD
 * 
 * @author André Valadas
 */
public class WebAppPropertiesListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	System.out.println("WebAppPropertiesListener.contextInitialized()");
        //final String rootPath = sce.getServletContext().getRealPath("/");
        //System.setProperty("webroot", rootPath+"WEB-INF/classes/database/");
        System.setProperty("webroot", "C:/Users/User/workspace/imoveis-service/src/main/resources/database/");
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("WebAppPropertiesListener.contextDestroyed()");
	}
}