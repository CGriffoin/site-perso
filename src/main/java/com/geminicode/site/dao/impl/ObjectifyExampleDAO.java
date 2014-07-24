package com.geminicode.site.dao.impl;

import com.google.common.collect.Maps;
import com.geminicode.site.dao.ExampleDAO;
import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import com.geminicode.site.model.impl.ObjectifyExample;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObjectifyExampleDAO extends ObjectifyGenericDAO<ObjectifyExample> implements ExampleDAO {

	public ObjectifyExampleDAO() {
		super(ObjectifyExample.class);
	}

	@Override
	public Example getByName(String name) throws IOException, NotFoundException {
		Map<String, Object> properties = Maps.newHashMap();
		properties.put("name", name);
		final List<ObjectifyExample> objectifyExamples = listByProperties(properties);

		if (!objectifyExamples.isEmpty()) {
			return objectifyExamples.get(0).fromPersistence();
		}
		throw new NotFoundException("Example with name '" + name + "' not found.");
	}

	@Override
	public void updateExample(Example example, Long id) throws NotFoundException, IOException {
		ObjectifyExample objectifyExample = example.getObjectifyExample();
		example.setId(id);
		update(objectifyExample);
	}
}
