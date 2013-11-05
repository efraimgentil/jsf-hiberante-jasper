package br.com.efraimgentil.exemple.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.filechooser.FileSystemView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.efraimgentil.exemple.dao.ExampleDAO;
import br.com.efraimgentil.exemple.domain.Example;

public class ExampleUsingMain {

	private static final boolean OPEN_FILE_AT_THE_END = true;
	
	public static void main(String[] args) {
						
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("example-pu");
		EntityManager entityManager =  factory.createEntityManager();

		ExampleDAO dao = new ExampleDAO(entityManager);
		
		EntityTransaction transaction =  entityManager.getTransaction();
		transaction.begin();
		int quantity = 10;
		for(int i = 0 ; i < quantity ; i++ ){
			dao.insert( new Example( String.format("Example %d", i) , "  No Description    " , new Date() ) );
		}
		transaction.commit();
		List<Example> listExemples =  dao.findAll(); 
		for (Example e : listExemples ) {
			System.out.println( e );
		}
		
		InputStream inputStream = ExampleUsingMain.class.getResourceAsStream("/reports/example.jasper");
		try {
			Map<String , Object> parameters = new HashMap<>();
			/*
			 *  You can pass parameters like this, or pass no parameters
			 */
			parameters.put("TITLE", "This is MY title");
//			net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
			JRDataSource beanDataSource = new JRBeanCollectionDataSource( listExemples );
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters , beanDataSource );
			String destFileName = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/example.pdf";
			System.out.println( destFileName );
			JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
			System.out.println("PDF was exported with success at " + destFileName );
			
			if(OPEN_FILE_AT_THE_END){
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File(destFileName);
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				    	System.out.println("Can't open the file sry");
				    }
				}else{
					System.out.println("Desktop is not supported");
				}
			}
			
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
}
