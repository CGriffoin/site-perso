package com.geminicode.site.serviceDto.impl;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import com.geminicode.site.model.dto.ExampleDto;
import com.geminicode.site.model.dto.ExampleListDto;
import com.geminicode.site.service.ExampleService;
import com.geminicode.site.service.ServiceSingleton;
import com.geminicode.site.serviceDto.ExampleServiceDto;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ExampleServiceDtoImpl implements ExampleServiceDto {

	private final static Logger LOGGER = Logger.getLogger(ExampleServiceDtoImpl.class.getSimpleName());

	private static final ExampleService EXAMPLE_SERVICE = ServiceSingleton.getExampleService();

	@Override
	public List<ExampleListDto> list() throws IOException {
		LOGGER.info("List Example");
		return ExampleListDto.extractDtos(EXAMPLE_SERVICE.list());
	}

	@Override
	public ExampleDto get(Long id) throws IOException, NotFoundException {
		LOGGER.info("Get Example : id=" + id );
		final Example example = EXAMPLE_SERVICE.get(id);
		return example.getExampleDto();
	}

	@Override
	public void create(ExampleDto exampleDto) throws IOException {
		LOGGER.info("Create Example");
		exampleDto.setId(EXAMPLE_SERVICE.create(exampleDto.getExample()).toString());
	}

	@Override
	public void update(ExampleDto exampleDto, Long id) throws IOException, NotFoundException {
		LOGGER.info("Update Example : id=" + id);
		EXAMPLE_SERVICE.update(exampleDto.getExample(), id);
	}

	@Override
	public void delete(Long id) throws NotFoundException, IOException {
		LOGGER.info("Delete Example : id=" + id);
		EXAMPLE_SERVICE.delete(id);
	}

	@Override
	public ExampleDto getByName(String name) throws IOException, NotFoundException {
		LOGGER.info("Get by Name Example : name=" + name);
		final Example example = EXAMPLE_SERVICE.getByName(name);
		return example.getExampleDto();
	}
}
