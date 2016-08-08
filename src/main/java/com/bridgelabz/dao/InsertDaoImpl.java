package com.bridgelabz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
/*import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;*/
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
/*import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;*/
import org.springframework.stereotype.Repository;

import com.bridgelabz.dto.EmployeeDto;

@Repository("insertImp")
public class InsertDaoImpl implements InsertDao {
	@Resource
	SessionFactory sessionFactory;

	/*
	 * @PersistenceContext private EntityManager em;
	 */

	public boolean isInserted(EmployeeDto employeeDto) {
		// creating configuration
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();
		try {
			// Save the data in database.
			session.save(employeeDto);
			t.commit();
			session.close();
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public List<EmployeeDto> getEmployeeList() {
		Session session = sessionFactory.openSession();
		List<EmployeeDto> list = session.createQuery("from EmployeeDto").list();
		session.close();
		return list;
	}

	public void update(EmployeeDto employeeDto) {
		Session session = sessionFactory.openSession();

		Transaction t = session.beginTransaction();
		String queryString = "update EmployeeDto a set a.age=? where a.name=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, employeeDto.getAge());
		query.setParameter(1, employeeDto.getName());
		query.executeUpdate();
		t.commit();
		session.close();

	}

	public void delete(EmployeeDto employeeDto) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		String queryString = "delete EmployeeDto a where a.name=?";
		Query query = session.createQuery(queryString);
		query.setParameter(0, employeeDto.getName());
		query.executeUpdate();
		t.commit();
		session.close();
	}

	public List<String> displayName() {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(EmployeeDto.class);
		c.setProjection(Projections.property("name"));
		List<String> list = c.list();
		session.close();
		return list;
	}

	public List<EmployeeDto> getAssendingOrder() {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(EmployeeDto.class);
		c.add(Restrictions.gt("age", 20));
		c.addOrder(Order.asc("age"));
		List<EmployeeDto> list = c.list();
		session.close();
		return list;
	}

	public boolean isInserted(List<EmployeeDto> empList) {
		Session session = sessionFactory.openSession();
		try{
		Transaction tx = session.beginTransaction();

		for ( int i=0; i<empList.size(); i++ ) {
		    session.save(empList.get(i));
		    //session.clear();
		    if ( i % 20 == 0 ) { 
		    	//20, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
		    }
		}
		tx.commit();
		return true;
		}catch(Exception exception){
			System.out.println("abccccc");
			return false;
		}
		finally{
			session.close();
		}
	}

	public List<EmployeeDto> getTenEmployeeList(int start) {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(EmployeeDto.class);
		c.add(Restrictions.ge("id", start));
		c.setMaxResults(10);
		c.addOrder(Order.asc("id"));
		List<EmployeeDto> list = c.list();
		session.close();
		return list;
	}

}
