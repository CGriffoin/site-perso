package com.geminicode.site.service;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface ExampleService {

	List<Example> list() throws IOException;

	List<Example> list(Collection<Long> ids) throws IOException;

	Example get(Long id) throws IOException, NotFoundException;

	Example getByName(String name) throws NotFoundException, IOException;

	Long create(Example example) throws IOException;

	void update(Example example, Long id) throws IOException, NotFoundException;

	void delete(Long id) throws IOException, NotFoundException;

}
