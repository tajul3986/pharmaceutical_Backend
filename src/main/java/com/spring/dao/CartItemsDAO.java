package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.CartItems;



@Repository(value = "cartItemsDAO")
@Transactional
public class CartItemsDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public CartItems save(CartItems ci){
    	getSession().save(ci);
//    	getSession().merge(ci);
    	getSession().flush();
        return ci;
    }

    public List<CartItems> getAll(){
    	String sql = "from cartItems";
        List<CartItems> ci = getSession().createQuery(sql).list();
        return ci;
    }

//    public CartItems getUserByUsername(CartItems user) {
//        String sql = "from user where username = '" + cartItems.getUsername() + "'";
//        List<CartItems> userList = getSession().createQuery(sql).list();
//        return userList.get(0);
//    }
    
    public CartItems getCartItemsById(long id) {
        String sql = "from cartItems where id = '" + id + "'";
        List<CartItems> cartList = getSession().createQuery(sql).list();
        return cartList.get(0);

    }
    
//    public CartItems getCartItemsById(Long id) {
//        return entityManager.find(CartItems.class, id);
//    }

//    public CartItems update(CartItems ci) {
//        String hql = "update cartItems set u_name = '"+ci.getName()+"', e_mail = '"+ci.getEmail()+"', u_phone = '"+ci.getPhone()+"', usr_name = '"+ci.getUsername()+"', u_password = '"+ci.getPassword()+"', u_confirmpassword = '"+ci.getConfirmpassword()+"'   where id = '"+ci.getId()+"'";
//        Query q = getSession().createQuery(hql);
//        q.executeUpdate();
//        getSession().flush();
//        return u;
//    }

    
//    public CartItems delete(CartItems ci) {
//        String sql = "delete from cartItems where id = :id";
//        int deletedCount = getSession().createQuery(sql)
//                              .setParameter("id", ci.getId())
//                              .executeUpdate();
//        
//        return ci;
//    }
    
    public CartItems delete(CartItems c) {
        String sql = "delete cartItems where id = '" + c.getId() + "'";
        int delete = getSession().createQuery(sql).executeUpdate();
        return c;
      }

}
