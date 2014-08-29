package br.com.efraimgentil.exemple.main;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.efraimgentil.exemple.domain.ComplexExample;
import br.com.efraimgentil.exemple.domain.Example;

public class AppMain {

  public static void main(String[] args) {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("example-pu");
    EntityManager entityManager = factory.createEntityManager();

    ComplexExample ce = new ComplexExample();
    ce.setName("My example");
    int quantity = 10;
    for (int i = 0; i < quantity; i++) {
      Example example =
          new Example(String.format("Example %d", i), "  No Description    ", new Date());
      example.setComplexExample(ce);
      ce.getExamples().add(example);
    }
   
   entityManager.getTransaction().begin(); 
    entityManager.persist(ce);;
    entityManager.getTransaction().commit(); 
    
    Query query = entityManager.createQuery("SELECT e FROM Example e");
    List<Example> resultList = query.getResultList();
    for (Example example : resultList) {
      System.out.println( example.getName() );
      System.out.println( example.getComplexExample().getName() );
      System.out.println( "___________________________________");
    }
  }

}
