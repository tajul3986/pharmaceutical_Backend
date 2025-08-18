package com.spring.dao;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Subcategory;



@Repository(value = "subcategoryDAO")
@Transactional
public class SubcategoryDAO {

	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Subcategory save(Subcategory sc){
    	getSession().save(sc);
    	getSession().flush();
        return sc;
    }

    public List<Subcategory> getAll(){
    	String sql = "from subcategory";
        List<Subcategory> scl = getSession().createQuery(sql).list();
        return scl;
    }

    public Subcategory getCategoryById(long cid) {
        String sql = "from subcategory where id = '" + cid + "'";
        List<Subcategory> catList = getSession().createQuery(sql).list();
        return catList.get(0);

    }

    public Subcategory update(Subcategory sc) {
        String hql = "update subcategory set subcategory_name = '"+sc.getName()+"', category_id = '"+sc.getCategoryId()+"', category_name = '"+sc.getCategoryName()+"' where id = '"+sc.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return sc;
    }


//    public Category delete(Category c) {
//    	String sql = "delete category where id = '"+c.getId()+"'";
//        int delete = getSession().createQuery(sql).executeUpdate();
//        return c;
//    }
    
    public Subcategory delete(Subcategory sc) {
        String sql = "delete from subcategory where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", sc.getId())
                              .executeUpdate();
        
        return sc;
    }

}
