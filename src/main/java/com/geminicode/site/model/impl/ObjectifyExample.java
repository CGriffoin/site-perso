package com.geminicode.site.model.impl;

import com.google.appengine.api.datastore.Text;
import com.google.common.base.Objects;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.geminicode.site.model.Example;
import com.geminicode.site.utils.HasTechnicalId;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

@Cache
@Entity
public class ObjectifyExample implements Serializable, HasTechnicalId {

	@Id
	private Long technicalId = null;

	@Index
	private Long id = null;

	@Index
	private String name = null;

	private Text json = null;

	public Example fromPersistence() throws IOException {

		final ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json.getValue(), Example.class);
	}

	public Long getTechnicalId() {
		return technicalId;
	}

	public void setTechnicalId(Long technicalId) {
		this.technicalId = technicalId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJson() {
		return json.getValue();
	}

	public void setJson(String json) {
		this.json = new Text(json);
	}

	public void setJson(Text json) {
		this.json = json;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ObjectifyExample) {
			final ObjectifyExample that = (ObjectifyExample) obj;
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
				.add("technicalId", technicalId)
				.add("id", id)
				.add("name", name)
				.add("json", json)
				.toString();
	}
}
