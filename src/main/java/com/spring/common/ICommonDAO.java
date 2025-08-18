package com.spring.common;

import java.util.List;

public interface ICommonDAO<T, ID>{
	
	public T save(T t);

	public T update(T t);

	public T  delete(T t);

	public List<T> getAll();

	public T getById(long id);


}
