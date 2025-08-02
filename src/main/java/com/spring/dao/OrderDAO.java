package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.spring.common.ICommonDAO;
import com.spring.enums.OrderStatus;
import com.spring.model.Order;
import com.spring.model.TopMedicine;

@Repository(value = "orderDAO")
@Transactional
public class OrderDAO implements ICommonDAO<Order, Long> {
	
	

	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Order save(Order order) {
//        getSession().save(order);
        getSession().merge(order);
        getSession().flush();
        return order;
    }

    @Override
    public List<Order> getAll() {
        String hql = "from orders";
        return getSession().createQuery(hql, Order.class).getResultList();
    }

    @Override
    public Order getById(long id) {
        return getSession().get(Order.class, id);
    }

    @Override
    public Order update(Order order) {
        getSession().update(order);
        getSession().flush();
        return order;
    }

    @Override
    public Order delete(Order order) {
        getSession().delete(order);
        getSession().flush();
        return order;
    }

	
	
	
	
	
	//with approval
	
//	@Autowired
//    private JdbcTemplate jdbcTemplate;
//	
//
//	
//	@Autowired
//    private EntityManager entityManager;
//
//    private Session getSession() {
//        return entityManager.unwrap(Session.class);
//    }
//
//    @Override
//    public Order save(Order order) {
////        getSession().save(order);
//    	Order merged = (Order)getSession().merge(order);
//        getSession().flush();
//        return merged;
//    }
//
//    @Override
//    public List<Order> getAll() {
//        String hql = "from orders";
//        return getSession().createQuery(hql, Order.class).getResultList();
//    }
//
//    @Override
//    public Order getById(long id) {
//        return getSession().get(Order.class, id);
//    }
//
//    @Override
//    public Order update(Order order) {
//        getSession().update(order);
//        getSession().flush();
//        return order;
//    }
//
//    @Override
//    public Order delete(Order order) {
//        getSession().delete(order);
//        getSession().flush();
//        return order;
//    }
    

    
    
    
	
//	@Autowired
//    private EntityManager entityManager;
//
//    private Session getSession() {
//        return entityManager.unwrap(Session.class);
//    }
//
//    @Override
//    public Order save(Order order) {
//        getSession().saveOrUpdate(order);  // works for new and detached entities
//        getSession().flush();
//        return order;
//    }
//
//    @Override
//    public List<Order> getAll() {
//        String hql = "from orders";
//        return getSession().createQuery(hql, Order.class).getResultList();
//    }
//
//    @Override
//    public Order getById(long id) {
//        return getSession().get(Order.class, id);
//    }
//
//    @Override
//    public Order update(Order order) {
//        getSession().update(order);
//        getSession().flush();
//        return order;
//    }
//
//    @Override
//    public Order delete(Order order) {
//        getSession().delete(order);
//        getSession().flush();
//        return order;
//    }
//
//    // Safe query example
//    public List<Order> getByTransactionId(String txId) {
//        String hql = "FROM orders o WHERE o.transactionId = :txId";
//        return getSession().createQuery(hql, Order.class)
//                           .setParameter("txId", txId)
//                           .getResultList();
//    }

}
