package com.geminicode.site.model.dto;

import com.google.appengine.repackaged.com.google.common.base.Strings;
import com.google.common.base.Objects;
import com.geminicode.site.model.Example;
import com.geminicode.site.utils.HasStringId;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

public class ExampleDto implements Serializable, HasStringId {

	private String id = "";
	private String name = "";

	@JsonIgnore
	public Example getExample() {
		final Example example = new Example();
		if(!Strings.isNullOrEmpty(id)){
			example.setId(Long.valueOf(id));
		}
		example.setName(name);

		return example;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
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
	public boolean equals(Object obj) {
		if (obj instanceof ExampleDto) {
			final ExampleDto that = (ExampleDto) obj;
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
}
