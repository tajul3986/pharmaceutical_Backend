package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.common.ICommonDAO;
import com.spring.model.OrderItems;




@Repository(value = "orderItemsDAO")
@Transactional
public class OrderItemsDAO implements ICommonDAO<OrderItems, Long> {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public OrderItems save(OrderItems oi){
    	getSession().save(oi);
    	getSession().flush();
        return oi;
    }

    public List<OrderItems> getAll(){
    	String sql = "from orderitems";
        List<OrderItems> oit = getSession().createQuery(sql).list();
        return oit;
    }

    public OrderItems getById(long oid) {
        String sql = "from orderitems where id = '" + oid + "'";
        List<OrderItems> oiList = getSession().createQuery(sql).list();
        return oiList.get(0);

    }

    public OrderItems update(OrderItems oi) {
        String hql = "update orderitems set product_name = '"+oi.getName()+"', product_quantity = '"+oi.getQuantity()+"', product_price = '"+oi.getPrice()+"' where id = '"+oi.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return oi;
    }

    
//    public OrderItems delete(OrderItems oi) {
//        String sql = "delete from orderitems where id = :id";
//        int deletedCount = getSession().createQuery(sql)
//                              .setParameter("id", c.getId())
//                              .executeUpdate();
//        
//        return oi;
//    }
    
    public OrderItems delete(OrderItems oi) {
        String sql = "delete orderitems where id = '" + oi.getId() + "'";
        int delete = getSession().createQuery(sql).executeUpdate();
        return oi;
      }

	

}
