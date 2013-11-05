package br.com.efraimgentil.exemple.business;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.efraimgentil.exemple.dao.ExampleDAO;
import br.com.efraimgentil.exemple.domain.Example;
import br.com.efraimgentil.exemple.util.MyEntityManagerFactory;

public class ExampleBS implements Serializable {
	
	private static final long serialVersionUID = -7568733669006157845L;

	private ExampleDAO exempleDAO;
	
	private EntityManager entityManager;
	
	public ExampleBS() {
		setEntityManager(MyEntityManagerFactory.createEntityManager());
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
