package br.com.efraimgentil.exemple.managedbean;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.efraimgentil.exemple.business.ExampleBS;
import br.com.efraimgentil.exemple.dao.ExampleDAO;
import br.com.efraimgentil.exemple.domain.Example;
import br.com.efraimgentil.exemple.util.MyEntityManagerFactory;

@ManagedBean
@RequestScoped
public class ExampleFormMB implements Serializable {

	private static final long serialVersionUID = 1758718052118631435L;

	private Example example ;
	
	private ExampleBS exampleBS;
	
	public ExampleFormMB() {
		example = new Example();	
	}

	public ExampleFormMB(ExampleDAO exampleDAO) {
		super();
	}

	public String create() {
		exampleBS = new ExampleBS();
		exampleBS.insert( getExample() );
		return "/list_example.jsf?faces-redirect=true";
	}

	public Example getExample() {
		return example;
	}

	public void setExample(Example example) {
		this.example = example;
	}
	
	@PreDestroy
	public void destroy(){
		MyEntityManagerFactory.closeEntityManager( exampleBS.getEntityManager() );
	}

}
