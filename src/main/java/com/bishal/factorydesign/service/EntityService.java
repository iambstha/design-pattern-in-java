package com.bishal.factorydesign.service;

public interface EntityService<T> {
	T createEntity(T entity); 
	T readEntity(Integer id);
	T updateEntity(Integer id, T updatedEntity);
	boolean deleteEntity(Integer id);
}