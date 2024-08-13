package com.sampleProject.evse.provision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class EvseProvisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvseProvisionApplication.class, args);
//
//		DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
//		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
	}

}
