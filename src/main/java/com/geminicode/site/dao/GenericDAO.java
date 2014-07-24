package com.geminicode.site.dao;


import com.geminicode.site.exception.NotFoundException;
import com.geminicode.site.utils.HasTechnicalId;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GenericDAO<E extends HasTechnicalId> {

	List<E> list();

	List<E> list(Collection<Long> ids) throws IOException;

	E getByProperties(Map<String, Object> properties) throws NotFoundException;

	List<E> listByProperties(Map<String, Object> properties);

	E get(Long id) throws NotFoundException, IOException;

	E getWithParent(Long id, Long ancestorId) throws NotFoundException, IOException;

	Long create(E entity) throws IOException;

	void create(List<E> entities) throws IOException;

	Long createWithParent(E entity, Long ancestorId) throws IOException, NotFoundException;

	void createWithParent(List<E> entities, Long ancestorId) throws IOException, NotFoundException;

	void update(E entity) throws NotFoundException, IOException;

	void update(List<E> entities) throws NotFoundException, IOException;

	void updateWithParent(E entity, Long id, Long ancestorId) throws NotFoundException, IOException;

	void updateWithParent(List<E> entities, Long ancestorId) throws NotFoundException, IOException;

	void delete(Long id) throws IOException, NotFoundException;

	void delete(Collection<Long> ids) throws IOException;

	void deleteWithParent(Long id, Long ancestorId) throws NotFoundException, IOException;

}
