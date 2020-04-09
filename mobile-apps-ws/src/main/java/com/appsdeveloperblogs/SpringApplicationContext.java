package com.appsdeveloperblogs;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
    public static ApplicationContext CONTEXT;  
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext Context) throws BeansException {
		CONTEXT = Context;

	}
	public static Object getBean(String beanName) {
		
		return CONTEXT.getBean(beanName);
	}

}
