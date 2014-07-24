package com.geminicode.site.dao.impl;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.DeleteType;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.Saver;
import com.geminicode.site.configuration.AppConfig;
import com.geminicode.site.dao.GenericDAO;
import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.utils.HasTechnicalId;
import com.geminicode.site.utils.IdHelper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ObjectifyGenericDAO<E extends HasTechnicalId> implements GenericDAO<E> {

	private final static Logger LOGGER = Logger.getLogger(ObjectifyGenericDAO.class.getSimpleName());

	private final AppConfig config = AppConfig.getConfig();

	private Class<E> clazz = null;


	public ObjectifyGenericDAO(final Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public List<E> list() {
		LOGGER.info("List : clazz=" + clazz.getSimpleName());
		return Lists.newArrayList(getLoadQuery().list());
	}

	@Override
	public List<E> list(Collection<Long> ids) throws IOException {
		LOGGER.info("List By Ids : clazz=" + clazz.getSimpleName());

		final int maxByIds = config.getMaxByIds();

		final List<Long> allIds = Lists.newArrayList(ids);
		final List<Long> subIds = Lists.newArrayList();
		final List<E> entities = Lists.newArrayList();

		do {
			subIds.clear();

			final int toIndex = allIds.size() >= maxByIds ? maxByIds : allIds.size();
			subIds.addAll(allIds.subList(0, toIndex));

			final Collection<Long> technicalIds = getTechnicalIds(subIds);
			final Collection<E> subEntities = getLoadQuery().ids(technicalIds).values();
			entities.addAll(sortEntitiesByIds(subEntities, subIds));

			allIds.removeAll(subIds);

		} while (!allIds.isEmpty());

		return entities;
	}

	@Override
	public E getByProperties(Map<String, Object> properties) throws com.googlecode.objectify.NotFoundException {
		LOGGER.info("Get by property : clazz=" + clazz.getSimpleName());

		Query<E> query = getLoadQuery();

		for (final String property : properties.keySet()) {
			query = query.filter(property, properties.get(property));
		}

		return query.first().safe();

	}

	@Override
	public List<E> listByProperties(Map<String, Object> properties) {
		LOGGER.info("Get by property : clazz=" + clazz.getSimpleName());

		Query<E> query = getLoadQuery();
		for (String property : properties.keySet()) {
			query = query.filter(property, properties.get(property));
		}
		return Lists.newArrayList(query.list());
	}

	@Override
	public E get(Long id) throws NotFoundException, IOException {
		LOGGER.info("Get id : clazz=" + clazz.getSimpleName() + ", id=" + id);

		final Long technicalId = getTechnicalId(id);

		try {
			return getLoadQuery().id(technicalId).safe();

		} catch (com.googlecode.objectify.NotFoundException ignored) {
			throw new NotFoundException(clazz.getSimpleName() + " with id '" + id + "' not found.");
		}
	}

	@Override
	public E getWithParent(Long id, Long ancestorId) throws NotFoundException, IOException {
		final Long technicalId = getTechnicalIdWithParent(id, ancestorId);

		try {
			return getLoadQuery().id(technicalId).safe();

		} catch (com.googlecode.objectify.NotFoundException ignored) {
			throw new NotFoundException(clazz.getSimpleName() + " with id '" + id + "' not found.");
		}
	}

	@Override
	public Long create(E entity) throws IOException {
		LOGGER.info("Create : clazz=" + clazz.getSimpleName());
		return save(entity).getId();
	}

	@Override
	public void create(List<E> entities) throws IOException {
		LOGGER.info("Create : clazz=" + clazz.getSimpleName());
		save(entities);
	}

	@Override
	public Long createWithParent(E entity, Long ancestorId) throws IOException, NotFoundException {
		LOGGER.info("Create : clazz=" + clazz.getSimpleName());

		return saveWithParent(entity, ancestorId).getId();
	}

	@Override
	public void createWithParent(List<E> entities, Long ancestorId) throws IOException, NotFoundException {
		LOGGER.info("Create : clazz=" + clazz.getSimpleName());

		saveWithParent(entities, ancestorId);
	}

	@Override
	public void update(E entity) throws NotFoundException, IOException {
		LOGGER.info("Update id : clazz=" + clazz.getSimpleName() + ", id=" + entity.getId());

		final Long technicalId = getTechnicalId(entity.getId());
		entity.setTechnicalId(technicalId);

		save(entity);
	}

	@Override
	public void update(List<E> entities) throws NotFoundException, IOException {
		LOGGER.info("Update ids : clazz=" + clazz.getSimpleName());
		for (E entity : entities) {
			final Long technicalId = getTechnicalId(entity.getId());
			entity.setTechnicalId(technicalId);
		}

		save(entities);
	}

	@Override
	public void updateWithParent(E entity, Long id, Long ancestorId) throws NotFoundException, IOException {
		LOGGER.info("Update id : clazz=" + clazz.getSimpleName() + ", id=" + id);

		final Long technicalId = getTechnicalIdWithParent(id, ancestorId);
		entity.setTechnicalId(technicalId);

		saveWithParent(entity, ancestorId);
	}

	@Override
	public void updateWithParent(List<E> entities, Long ancestorId) throws NotFoundException, IOException {
		LOGGER.info("Update ids : clazz=" + clazz.getSimpleName());

		for (E entity : entities) {
			final Long technicalId = getTechnicalIdWithParent(entity.getId(), ancestorId);
			entity.setTechnicalId(technicalId);
		}
		saveWithParent(entities, ancestorId);
	}

	@Override
	public void delete(Long id) throws IOException, NotFoundException {
		LOGGER.info("Delete : clazz=" + clazz.getSimpleName() + ", id=" + id);

		final Long technicalId = getTechnicalId(id);
		getDeleteQuery().id(technicalId).now();
	}

	@Override
	public void delete(Collection<Long> ids) throws IOException {

		final Collection<Long> technicalIds = getTechnicalIds(ids);
		getDeleteQuery().ids(technicalIds).now();
	}

	@Override
	public void deleteWithParent(Long id, Long ancestorId) throws NotFoundException, IOException {
		LOGGER.info("Delete With Parent : clazz=" + clazz.getSimpleName() + ", id=" + id);

		final Long technicalId = getTechnicalIdWithParent(id, ancestorId);
		getDeleteQuery().id(technicalId).now();
	}

	private Key<E> save(E entity) throws IOException {
		LOGGER.info("Save : clazz=" + clazz.getSimpleName());

		final Key<E> technicalKey = getSaveQuery().entity(entity).now();
		return technicalKey;
	}

	private List<Key<E>> save(List<E> entities) throws IOException {
		LOGGER.info("Save : clazz=" + clazz.getSimpleName());

		final Map<Key<E>, E> technicalKey2Entities = getSaveQuery().entities(entities).now();
		return Lists.newArrayList(technicalKey2Entities.keySet());
	}

	private Key<E> saveWithParent(E entity, Long ancestorId) throws IOException, NotFoundException {
		LOGGER.info("Save : clazz=" + clazz.getSimpleName());

		final Key<E> technicalKey = getSaveQuery().entity(entity).now();
		return technicalKey;
	}

	private void saveWithParent(List<E> entities, Long ancestorId) throws IOException, NotFoundException {
		LOGGER.info("Save : clazz=" + clazz.getSimpleName());

		getSaveQuery().entities(entities).now();
	}

	private Long getTechnicalId(Long id) throws IOException, NotFoundException {
		try {
			final E entity = getLoadQuery().filter("id", id).first().safe();
			return entity.getTechnicalId();

		} catch (com.googlecode.objectify.NotFoundException ignored) {
			throw new NotFoundException(clazz.getSimpleName() + " with id '" + id + "' not found. ");
		}
	}

	private Collection<Long> getTechnicalIds(Collection<Long> ids) throws IOException {
		final Map<String, Object> properties = Maps.newHashMap();
		properties.put("id", ids);
		final List<E> entities = listByProperties(properties);

		return IdHelper.ids(entities);
	}

	private Long getTechnicalIdWithParent(Long id, Long ancestorId) throws IOException, NotFoundException {

		try {
			final E entity = getLoadQuery().filter("id", id).filter("ancestorId", ancestorId).first().safe();
			return entity.getTechnicalId();

		} catch (com.googlecode.objectify.NotFoundException ignored) {
			throw new NotFoundException(clazz.getSimpleName() + " with id '" + id + "' not found in ancestor '" + ancestorId + "'. ");
		}
	}

	private LoadType<E> getLoadQuery() {
		return ObjectifySingleton.get().load().type(clazz);
	}

	private Saver getSaveQuery() {
		return ObjectifySingleton.get().save();
	}

	private DeleteType getDeleteQuery() {
		return ObjectifySingleton.get().delete().type(clazz);
	}

	private Collection<E> sortEntitiesByIds(Collection<E> subEntities, Collection<Long> subIds) {
		final Map<Long, E> id2entities = IdHelper.id2Entities(subEntities);
		final Function<Long, E> getEntity = new Function<Long, E>() {
			@Override
			public E apply(Long input) {
				return id2entities.get(input);
			}
		};
		return Collections2.transform(subIds, getEntity);
	}

}
