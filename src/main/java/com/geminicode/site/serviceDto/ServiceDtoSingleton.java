package com.geminicode.site.serviceDto;

import com.geminicode.site.serviceDto.impl.ExampleServiceDtoImpl;

public class ServiceDtoSingleton {

	private static final ServiceDtoSingleton INSTANCE = new ServiceDtoSingleton();

	private ExampleServiceDto exampleServiceDto;


	public static ExampleServiceDto getExampleServiceDto() {if (INSTANCE.exampleServiceDto == null) {
		INSTANCE.exampleServiceDto = new ExampleServiceDtoImpl();
	}
		return INSTANCE.exampleServiceDto;
	}
}
