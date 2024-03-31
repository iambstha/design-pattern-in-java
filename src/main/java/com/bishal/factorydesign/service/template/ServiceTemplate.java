package com.bishal.factorydesign.service.template;

public interface ServiceTemplate<T> {
	T createEntity(T entity);

	T readEntity(Integer id);

	T updateEntity(Integer id, T updatedEntity);

	boolean deleteEntity(Integer id);
}