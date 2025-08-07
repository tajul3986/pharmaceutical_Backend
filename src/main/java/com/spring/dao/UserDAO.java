package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.User;



@Repository(value = "userDAO")
@Transactional
public class UserDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

//    public User save(User u){
//    	getSession().save(u);
//    	getSession().flush();
//        return u;
//    }
    
    
    //rolebased
    public User save(User u){
        getSession().saveOrUpdate(u);
        getSession().flush();
        return u;
    }

    public List<User> getAll(){
    	String sql = "from user";
        List<User> us = getSession().createQuery(sql).list();
        return us;
    }

//    public User getUserByUsername(User user) {
//        String sql = "from user where username = '" + user.getUsername() + "'";
//        List<User> userList = getSession().createQuery(sql).list();
//        return userList.get(0);
//
//    }
    
  //rolebased
    public User getUserByUsername(String username) {
        String sql = "from user where username = :username";
        List<User> userList = getSession()
                .createQuery(sql, User.class)
                .setParameter("username", username)
                .getResultList();

        return userList.isEmpty() ? null : userList.get(0);
    }

  //rolebased
    public User login(String username, String password) {
        String sql = "from user where username = :username and password = :password";
        List<User> userList = getSession()
                .createQuery(sql, User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();

        return userList.isEmpty() ? null : userList.get(0);
    }
    
    
    
    public User getUserById(long uid) {
        String sql = "from user where id = '" + uid + "'";
        List<User> userList = getSession().createQuery(sql).list();
        return userList.get(0);

    }

    public User update(User u) {
        String hql = "update user set u_name = '"+u.getName()+"', e_mail = '"+u.getEmail()+"', u_phone = '"+u.getPhone()+"', usr_name = '"+u.getUsername()+"', u_password = '"+u.getPassword()+"', u_confirmpassword = '"+u.getConfirmpassword()+"'   where id = '"+u.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return u;
    }

    
    public User delete(User u) {
        String sql = "delete from user where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", u.getId())
                              .executeUpdate();
        
        return u;
    }

}
