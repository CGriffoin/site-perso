package com.geminicode.site.service;

import com.geminicode.site.service.impl.ExampleServiceImpl;

public class ServiceSingleton {

	private static final ServiceSingleton INSTANCE = new ServiceSingleton();

	private ExampleService exampleService = null;

	public static ExampleService getExampleService() {
		if (INSTANCE.exampleService == null) {
			INSTANCE.exampleService = new ExampleServiceImpl();
		}
		return INSTANCE.exampleService;
	}
}
