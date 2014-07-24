package com.geminicode.site.model;

import com.geminicode.site.model.dto.ExampleDto;
import com.geminicode.site.model.dto.ExampleListDto;
import com.geminicode.site.utils.IdHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ExampleTest {

	@Test
	public void testGetExampleDto() {

		final Long id = IdHelper.generateId();
		final String name = "example";

		//given

		final Example example = new Example();
		example.setId(id);
		example.setName(name);


		//then

		final ExampleDto exampleDto = example.getExampleDto();


		//assert

		assertThat(exampleDto).isNotNull();
		assertThat(exampleDto.getId()).isEqualTo(example.getId().toString());
		assertThat(exampleDto.getName()).isEqualTo(example.getName());

	}

	@Test
	public void testGetExampleListDto() {

		final Long id = IdHelper.generateId();
		final String name = "example";

		//given

		final Example example = new Example();
		example.setId(id);
		example.setName(name);

		//then
		final ExampleListDto exampleListDto = example.getExampleListDto();

		//assert
		assertThat(exampleListDto).isNotNull();
		assertThat(exampleListDto.getId()).isEqualTo(example.getId().toString());
		assertThat(exampleListDto.getName()).isEqualTo(example.getName());

	}
}
