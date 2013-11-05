package br.com.efraimgentil.exemple.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

public class MyEntityManagerFactory {
	
	
	private static EntityManagerFactory factory;
	
	public MyEntityManagerFactory() {	}
	
	public static EntityManager createEntityManager(){
		if(factory == null){
			factory = Persistence.createEntityManagerFactory("example-pu");
		}
		return factory.createEntityManager();
	}
	
	public static void closeEntityManager( EntityManager entityManager ){
		try{
			if( entityManager.isOpen() ){
				if( entityManager.getTransaction().isActive() )
					entityManager.flush();
				entityManager.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
