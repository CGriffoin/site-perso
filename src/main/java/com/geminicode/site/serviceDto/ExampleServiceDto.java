package com.geminicode.site.serviceDto;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.dto.ExampleDto;
import com.geminicode.site.model.dto.ExampleListDto;

import java.io.IOException;
import java.util.List;

public interface ExampleServiceDto {

	List<ExampleListDto> list() throws IOException;

	ExampleDto get(Long id) throws IOException, NotFoundException;

	void create(ExampleDto exampleDto) throws IOException;

	void update(ExampleDto exampleDto, Long id) throws IOException, NotFoundException;

	void delete(Long id) throws NotFoundException, IOException;

	ExampleDto getByName(String name) throws IOException, NotFoundException;
}
