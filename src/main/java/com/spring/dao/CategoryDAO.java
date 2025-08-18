package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Category;

@Repository(value = "categoryDAO")
@Transactional
public class CategoryDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Category save(Category c){
    	getSession().save(c);
    	getSession().flush();
        return c;
    }

    public List<Category> getAll(){
    	String sql = "from category";
        List<Category> cts = getSession().createQuery(sql).list();
        return cts;
    }

    public Category getCategoryById(long cid) {
        String sql = "from category where id = '" + cid + "'";
        List<Category> catList = getSession().createQuery(sql).list();
        return catList.get(0);

    }

    public Category update(Category c) {
        String hql = "update category set name = '"+c.getName()+"' where id = '"+c.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return c;
    }

    
    public Category delete(Category c) {
        String sql = "delete from category where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", c.getId())
                              .executeUpdate();
        
        return c;
    }


}
