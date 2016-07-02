package com.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDaoI<T> {

	@Autowired  
    public void setSessionFactoryOverride(SessionFactory sessionFactory)  
    {  
  
        super.setSessionFactory(sessionFactory);  
    } 
	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().save(o);
		
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String queryString,Object values){
		List<T> find = (List<T>) getHibernateTemplate().find(queryString, values);
		return find;
	}

	@Override
	public void delete(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(o);
		
	}
	
	

	public void deleteAll(List<T> t){
		getHibernateTemplate().deleteAll(t);
	}
	public void deleteSql(String queryString,Object values) {
		// TODO Auto-generated method stub
		List<T> find = this.find(queryString,values);
		deleteAll(find);
		
	}
	
}

