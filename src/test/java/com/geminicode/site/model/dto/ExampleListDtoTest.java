package com.geminicode.site.model.dto;

import com.google.common.collect.Lists;
import com.geminicode.site.model.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExampleListDtoTest {

	@Test
	public void testExtractDtos() {

		//given
		Example example = new Example();
		Example exampleSpy = spy(example);

		List<Example> examples = Lists.newArrayList(exampleSpy);

		//then
		final List<ExampleListDto> exampleListDtos = ExampleListDto.extractDtos(examples);

		//assert
		assertThat(exampleListDtos).isNotNull();
		assertThat(exampleListDtos).hasSize(examples.size());
		verify(exampleSpy, atLeastOnce()).getExampleListDto();
	}
}
