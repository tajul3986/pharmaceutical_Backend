package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.common.ICommonDAO;
import com.spring.model.Product;

import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository(value = "productDAO")
@Transactional
public class ProductDAO implements ICommonDAO<Product, Long>{
	


@Autowired
private EntityManager entityManager;

private Session getSession() {
  return entityManager.unwrap(Session.class);
}

public Product save(Product p) {
  getSession().save(p);
  getSession().flush();
  return p;
}

public List<Product> getAll() {
  String sql = "from product";
  List<Product> product = getSession().createQuery(sql).list();
  return product;
}

public Product getById(long id) {
  String sql = "from products where id = '" + id + "'";
  List<Product> ecomList = getSession().createQuery(sql).list();
  return ecomList.get(0);

}

public Product update(Product p) {
  String hql = "update products set product_code = '" + p.getProductCode() + "', name = '" + p.getName()
      + "', price = '" + p.getPrice() + "', category = '" + p.getCategory() + "'"
      + ", subcategory = '" + p.getSubcategory() + "',description = '" + p.getDescription()
      + "', stock = '" + p.getStock() + "', price = '" + p.getPrice() + "', stock = '" + p.getStock()
      + "', image = '" + p.getImage() + "' where id = '"
      + p.getId() + "'";
  Query q = getSession().createQuery(hql);
  q.executeUpdate();
  getSession().flush();
  return p;
}

public Product delete(Product p) {
  String sql = "delete product where id = '" + p.getId() + "'";
  int delete = getSession().createQuery(sql).executeUpdate();
  return p;
}


}
