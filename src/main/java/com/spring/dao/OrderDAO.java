package com.spring.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.common.ICommonDAO;
import com.spring.model.Order;

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

}
