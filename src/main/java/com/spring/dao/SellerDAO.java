package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Seller;


@Repository(value = "sellerDAO")
@Transactional
public class SellerDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Seller save(Seller s){
    	getSession().save(s);
    	getSession().flush();
        return s;
    }

    public List<Seller> getAll(){
    	String sql = "from seller";
        List<Seller> us = getSession().createQuery(sql).list();
        return us;
    }

    public Seller getUserById(long sid) {
        String sql = "from seller where id = '" + sid + "'";
        List<Seller> sellerList = getSession().createQuery(sql).list();
        return sellerList.get(0);

    }

    public Seller update(Seller s) {
        String hql = "update seller set s_name = '"+s.getName()+"', contact_email = '"+s.getContactEmail()+"', contact_phone = '"+s.getContactPhone()+"', s_address = '"+s.getAddress()+"'  where id = '"+s.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return s;
    }

    
    public Seller delete(Seller s) {
        String sql = "delete from seller where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", s.getId())
                              .executeUpdate();
        
        return s;
    }


}
