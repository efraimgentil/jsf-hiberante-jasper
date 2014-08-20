package br.com.efraimgentil.exemple.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.efraimgentil.exemple.domain.Example;

public class ComplexExampleDAO {
  
  private EntityManager entityManager;
  
  public ComplexExampleDAO(EntityManager entityManager) {
      this.entityManager = entityManager;
  }
  
  public void insert(Example exemple){
      entityManager.persist(exemple);
  }
  
  @SuppressWarnings("unchecked")
  public List<Example> findAll(){
      Query query = entityManager.createNamedQuery( Example.FIND_ALL );
      return query.getResultList();
  }
  
  
}
