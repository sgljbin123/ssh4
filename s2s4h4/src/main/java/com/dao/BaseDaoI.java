package com.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoI<T> {

	
	public Serializable save(T o);
	public List<T> findList(String queryString, Object[] values);
	public T find(String queryString, Object[] values);
	public void delete(T o);
	public void deleteAll(List<T> t);
	public void update(T o);
	public void saveOrUpdate(T o);
	public List<T> query(String queryString,Object[] values,int page,int row);
	public int count(String queryString,Object[] values);
}
