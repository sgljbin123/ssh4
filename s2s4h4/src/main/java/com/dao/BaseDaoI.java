package com.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDaoI<T> {

	
	public Serializable save(T o);
	public List<T> find(String querySql,Object param);
	public void delete(T o);
	public void deleteAll(List<T> t);
	public void deleteSql(String queryString,Object values);
}
