package com.geminicode.site.utils;

import com.google.common.collect.Lists;
import com.geminicode.site.model.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class IdHelperTest {


	@Test
	public void testId2Entities() {

		// given
		final Example example = new Example();
		final List<Example> examples = Lists.newArrayList(example);

		// then
		final Map<Long, Example> id2Examples = IdHelper.id2Entities(examples);

		// assert
		assertThat(id2Examples).isNotNull();
		assertThat(id2Examples).hasSize(examples.size());
		assertThat(id2Examples.containsKey(example.getId())).isTrue();
		assertThat(id2Examples.get(example.getId())).isEqualTo(example);
	}

	@Test
	public void testIds() {
		// given
		final Example example = new Example();
		final List<Example> examples = Lists.newArrayList(example);

		// then
		final List<Long> ids = IdHelper.ids(examples);

		// assert
		assertThat(ids).isNotNull();
		assertThat(ids).hasSize(examples.size());
		assertThat(ids).contains(example.getId());
	}

	@Test
	public void testIsValid_Negative() {
		// given
		Long id = -1L;

		// then
		final boolean valid = IdHelper.isValid(id);

		// assert
		assertThat(valid).isFalse();
	}

	@Test
	public void testIsValid_Positive() {
		// given
		Long id = 1L;

		// then
		final boolean valid = IdHelper.isValid(id);

		// assert
		assertThat(valid).isTrue();
	}

	@Test
	public void testIsValid_Zero() {
		// given
		Long id = 0L;

		// then
		final boolean valid = IdHelper.isValid(id);

		// assert
		assertThat(valid).isFalse();
	}
}
