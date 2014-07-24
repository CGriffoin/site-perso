package com.geminicode.site.utils;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class IdHelper {

	public static Long generateId() {
		return System.nanoTime();
	}

	public static <E extends HasId> Map<Long, E> id2Entities(Collection<E> entities) {
		Map<Long, E> id2Entities = Maps.newHashMap();

		for (E entity : entities) {
			id2Entities.put(entity.getId(), entity);
		}

		return id2Entities;
	}

	public static <E extends HasId> List<Long> ids(Collection<E> entities) {
		final Function<E, Long> extractId = new Function<E, Long>() {
			@Override
			public Long apply(E input) {
				return input.getId();
			}
		};

		return Lists.newArrayList(Collections2.transform(entities, extractId));
	}

	public static boolean isValid(Long id) {
		return id > 0;
	}

	public static <E extends HasTechnicalId> Collection<Long> technicalIds(List<E> entities) {
		final Function<E, Long> getTechnicalId = new Function<E, Long>() {
			@Override
			public Long apply(E input) {
				return input.getTechnicalId();
			}
		};
		return Collections2.transform(entities, getTechnicalId);
	}
}
