package com.geminicode.site.model.dto;

import com.geminicode.site.model.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ExampleDtoTest {

	@Test
	public void testGetExample() {

		final String id = "1";
		final String name = "name";

		//given
		ExampleDto exampleDto = new ExampleDto();
		exampleDto.setId((id));
		exampleDto.setName(name);

		//then
		Example example = exampleDto.getExample();

		//assert
		assertThat(example).isNotNull();
		assertThat(example.getId()).isEqualTo(Long.valueOf(id));
		assertThat(example.getName()).isEqualTo(name);
	}

}
