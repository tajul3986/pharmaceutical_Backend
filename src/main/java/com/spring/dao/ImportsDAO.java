package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Imports;


@Repository(value = "importsDAO")
@Transactional
public class ImportsDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public Imports save(Imports i){
    	getSession().save(i);
    	getSession().flush();
        return i;
    }

    public List<Imports> getAll(){
    	String sql = "from imports";
        List<Imports> imp = getSession().createQuery(sql).list();
        return imp;
    }

    public Imports getImportsById(long impid) {
        String sql = "from imports where id = '" + impid + "'";
        List<Imports> importsList = getSession().createQuery(sql).list();
        return importsList.get(0);

    }

    public Imports update(Imports i) {
        String hql = "update imports set seller_id = '"+i.getSellerId()+"', material_id = '"+i.getMaterialId()+"', seller_name = '"+i.getSellerName()+"', material_name = '"+i.getMaterialName()+"', m_quantity = '"+i.getQuantity()+"', unit_price = '"+i.getUnitPrice()+"', total_price = '"+i.getTotalPrice()+"', import_date = '"+i.getImportDate()+"'  where id = '"+i.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return i;
    }

    
    public Imports delete(Imports i) {
        String sql = "delete from imports where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", i.getId())
                              .executeUpdate();
        
        return i;
        
    }


}
