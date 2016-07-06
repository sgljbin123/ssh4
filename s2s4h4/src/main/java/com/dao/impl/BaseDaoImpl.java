package com.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
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
	public T find(String queryString, Object[] values){
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,values);
		return (find == null || find.size()==0)?null:find.get(0);
	}

	@Override
	public void delete(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(o);
		
	}
	
	

	public void deleteAll(List<T> t){
		getHibernateTemplate().deleteAll(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		List<T> find = (List<T>) getHibernateTemplate().find(queryString,values);
		return find;
	}
	@Override
	public void update(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(o);
		
	}
	@Override
	public void saveOrUpdate(T o) {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(o);
	}
	@Override
	public List<T> query(final String queryString, final Object[] values, final int page, final int row) {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().execute(new HibernateCallback<List>()
		    {
		     @Override
		 public List<T> doInHibernate(Session session) {
		       Query query = session.createQuery(queryString);
		         query.setFirstResult((page-1)*row);
		       query.setMaxResults(row);
		        for (int i = 0; i < values.length ; i++) {
		          query.setParameter(i, values[i]);
		        }
		        return query.list();
	     }
		    });
	     return list;
	}
	@Override
	public int count(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("select count(*)");
		queryString = sql.append(queryString).toString();
		List find = getHibernateTemplate().find(queryString, values);
		return (find == null || find.size()==0)?0:Integer.valueOf((String) find.get(0));
	}

}

