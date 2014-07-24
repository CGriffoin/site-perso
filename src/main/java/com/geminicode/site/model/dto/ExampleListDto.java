package com.geminicode.site.model.dto;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.geminicode.site.model.Example;
import com.geminicode.site.utils.HasStringId;

import java.io.Serializable;
import java.util.List;

public class ExampleListDto implements Serializable, HasStringId, Comparable<ExampleListDto> {

	private String id = "";
	private String name = "";


	public static List<ExampleListDto> extractDtos(final List<Example> examples) {

		final Function<Example, ExampleListDto> getExampleListDto = new Function<Example, ExampleListDto>() {
			@Override
			public ExampleListDto apply(Example input) {
				return input.getExampleListDto();
			}
		};

		return Lists.newArrayList(Collections2.transform(examples, getExampleListDto));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(final Object o) {
		if (o instanceof ExampleListDto) {
			final ExampleListDto that = (ExampleListDto) o;
			return Objects.equal(id, that.id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.toString();
	}

	@Override
	public int compareTo(ExampleListDto o) {
		return name.compareTo(o.getName());
	}

}
