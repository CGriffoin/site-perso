package com.geminicode.site.model;

import com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.geminicode.site.model.dto.ExampleDto;
import com.geminicode.site.model.dto.ExampleListDto;
import com.geminicode.site.model.impl.ObjectifyExample;
import com.geminicode.site.utils.HasId;
import com.geminicode.site.utils.IdHelper;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

@Cache
@Entity
public class Example implements HasId {

	private Long id = IdHelper.generateId();

	private String name = "";

	@JsonIgnore
	public ObjectifyExample getObjectifyExample() throws IOException {

		final ObjectMapper mapper = new ObjectMapper();

		final ObjectifyExample objectifyExample = new ObjectifyExample();
		objectifyExample.setId(id);
		objectifyExample.setName(name);
		objectifyExample.setJson(mapper.writeValueAsString(this));

		return objectifyExample;
	}

	@JsonIgnore
	public ExampleDto getExampleDto() {
		ExampleDto exampleDto = new ExampleDto();
		exampleDto.setId(id.toString());
		exampleDto.setName(name);

		return exampleDto;
	}

	@JsonIgnore
	public ExampleListDto getExampleListDto() {
		final ExampleListDto exampleListDto = new ExampleListDto();
		exampleListDto.setId(id.toString());
		exampleListDto.setName(name);

		return exampleListDto;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Example) {
			final Example that = (Example) obj;
			return Objects.equal(id, that.id);
		}
		return false;

	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.toString();
	}
}
