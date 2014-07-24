package com.geminicode.site.dao;

import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.model.Example;
import com.geminicode.site.model.impl.ObjectifyExample;

import java.io.IOException;

public interface ExampleDAO extends GenericDAO<ObjectifyExample> {

	Example getByName(String name) throws IOException, NotFoundException;

	void updateExample(Example example, Long id) throws NotFoundException, IOException;
}
