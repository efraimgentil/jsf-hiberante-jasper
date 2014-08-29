package br.com.efraimgentil.exemple.business;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.efraimgentil.exemple.dao.ExampleDAO;
import br.com.efraimgentil.exemple.domain.Example;
import br.com.efraimgentil.exemple.util.MyEntityManagerFactory;

public class ExampleBS implements Serializable {
	
	private static final long serialVersionUID = -7568733669006157845L;

	private ExampleDAO exempleDAO;
	
	private EntityManager entityManager;
	
	public ExampleBS() {
	   entityManager = MyEntityManagerFactory.createEntityManager();
		exempleDAO = new ExampleDAO(getEntityManager());
	}
	
	public void insert(Example exemple){
		EntityTransaction transaction = getEntityManager().getTransaction();
		transaction.begin();
		exempleDAO.insert(exemple);
		transaction.commit();
	}
	
	public ExampleDAO getExempleDAO() {
		return exempleDAO;
	}

	public void setExempleDAO(ExampleDAO exempleDAO) {
		this.exempleDAO = exempleDAO;
	}

	public List<Example> findAll() {
		return  exempleDAO.findAll();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	} 

}
