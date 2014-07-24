package com.geminicode.site.model.impl;

import com.geminicode.site.model.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ObjectifyExampleTest {

	@Test
	public void testFromPersistence() throws IOException {

		final Long id = 1L;
		final String name = "name";

		// given

		final ObjectifyExample objectifyExample = new ObjectifyExample();
		objectifyExample.setId(id);
		objectifyExample.setJson(
			"{" +
					"\"id\":" + id + "," +
					"\"name\": \"" + name + "\"" +
			"}");

		// then
		Example example = objectifyExample.fromPersistence();

		// assert
		assertThat(example.getId()).isEqualTo(id);
		assertThat(example.getName()).isEqualTo(name);
	}
}
