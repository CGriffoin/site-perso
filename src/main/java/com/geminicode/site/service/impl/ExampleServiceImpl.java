package com.geminicode.site.service.impl;

import com.google.common.collect.Lists;
import com.geminicode.site.dao.DAOSingleton;
import com.geminicode.site.dao.ExampleDAO;
import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import com.geminicode.site.model.impl.ObjectifyExample;
import com.geminicode.site.service.ExampleService;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ExampleServiceImpl implements ExampleService {

	private final static Logger LOGGER = Logger.getLogger(ExampleServiceImpl.class.getSimpleName());

	private static final ExampleDAO EXAMPLE_DAO = DAOSingleton.getExampleDao();

	@Override
	public List<Example> list() throws IOException {
		LOGGER.info("List Example");
		return listFromPersistence(EXAMPLE_DAO.list());
	}

	@Override
	public List<Example> list(Collection<Long> ids) throws IOException {
		return listFromPersistence(EXAMPLE_DAO.list(ids));
	}

	@Override
	public Example get(Long id) throws IOException, NotFoundException {
		LOGGER.info("Get Example : id=" + id);
		return EXAMPLE_DAO.get(id).fromPersistence();
	}

	@Override
	public Example getByName(String name) throws IOException, NotFoundException {
		LOGGER.info("Get By Name Example : name=" + name);
		return EXAMPLE_DAO.getByName(name);
	}

	@Override
	public Long create(Example example) throws IOException {
		LOGGER.info("Create Example : example=" + example.toString());
		EXAMPLE_DAO.create(example.getObjectifyExample());
		return example.getId();
	}

	@Override
	public void update(Example example, Long id) throws IOException, NotFoundException {
		LOGGER.info("Update Example : id=" + id);
		EXAMPLE_DAO.updateExample(example, id);
	}

	@Override
	public void delete(Long id) throws IOException, NotFoundException {
		LOGGER.info("Delete Example : id=" + id);
		EXAMPLE_DAO.delete(id);
	}

	private List<Example> listFromPersistence(List<ObjectifyExample> objectifyExamples) throws IOException {
		final List<Example> examples = Lists.newArrayList();

		for (ObjectifyExample objectifyExample : objectifyExamples) {
			examples.add(objectifyExample.fromPersistence());
		}
		return examples;
	}

}
