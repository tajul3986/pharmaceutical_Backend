package com.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.RawMaterial;

@Repository(value = "rawMaterialDAO")
@Transactional

public class RawMaterialDAO {
	
	@Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public RawMaterial save(RawMaterial rm){
    	getSession().save(rm);
    	getSession().flush();
        return rm;
    }

    public List<RawMaterial> getAll(){
    	String sql = "from rawmaterial";
        List<RawMaterial> rm = getSession().createQuery(sql).list();
        return rm;
    }

    public RawMaterial getRawMaterialById(long rmid) {
        String sql = "from rawmaterial where id = '" + rmid + "'";
        List<RawMaterial> rmList = getSession().createQuery(sql).list();
        return rmList.get(0);

    }

    public RawMaterial update(RawMaterial rm) {
        String hql = "update rawmaterial set rm_name = '"+rm.getName()+"', rm_code = '"+rm.getCode()+"',rm_description = '"+rm.getDescription()+"', rm_quantity = '"+rm.getQuantity()+"', rm_unit = '"+rm.getUnit()+"', price_perUnit = '"+rm.getPricePerUnit()+"', expiry_date = '"+rm.getExpiryDate()+"', batch_number = '"+rm.getBatchNumber()+"', storage_location = '"+rm.getStorageLocation()+"', rm_status = '"+rm.getStatus()+"'  where id = '"+rm.getId()+"'";
        Query q = getSession().createQuery(hql);
        q.executeUpdate();
        getSession().flush();
        return rm;
        
//        supplier_name = '"+rm.getSupplierName()+"',
//        supplier_id = '"+rm.getSupplierId()+"', purchase_date = '"+rm.getPurchaseDate()+"',
    }

    
    public RawMaterial delete(RawMaterial rm) {
        String sql = "delete from rawmaterial where id = :id";
        int deletedCount = getSession().createQuery(sql)
                              .setParameter("id", rm.getId())
                              .executeUpdate();
        
        return rm;
    }


}
