package br.com.efraimgentil.exemple.main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
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
import br.com.efraimgentil.exemple.domain.ComplexExample;
import br.com.efraimgentil.exemple.domain.Example;

public class ComplexExampleMain {

  private static final boolean OPEN_FILE_AT_THE_END = true;

  public static void main(String[] args) {

    ComplexExample ce = new ComplexExample();
    ce.setName("My example");
    int quantity = 10;
    for (int i = 0; i < quantity; i++) {
      ce.getExamples().add(
          new Example(String.format("Example %d", i), "  No Description    ", new Date()));
    }

    InputStream inputStream =
        ExampleUsingMain.class.getResourceAsStream("/reports/complexExample.jasper");
    try {
      Map<String, Object> parameters = new HashMap<>();
    
      /*
       * You can pass parameters like this, or pass no parameters
       */
      URL resource = ExampleUsingMain.class.getResource("/reports/");
      /*
       * With subReports you need to pass this parameter SUBREPORT_DIR to sinalize wich
       * folder ireports will find the subreport file
       */
      parameters.put("SUBREPORT_DIR", resource.getPath());

      JRDataSource beanDataSource = new JRBeanCollectionDataSource( Arrays.asList( ce ) );
      JasperPrint jasperPrint =
          JasperFillManager.fillReport(inputStream, parameters, beanDataSource);
      String destFileName =
          FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/complexExample.pdf";
      System.out.println(destFileName);
      JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
      System.out.println("PDF was exported with success at " + destFileName);

      if (OPEN_FILE_AT_THE_END) {
        if (Desktop.isDesktopSupported()) {
          try {
            File myFile = new File(destFileName);
            Desktop.getDesktop().open(myFile);
          } catch (IOException ex) {
            System.out.println("Can't open the file sry");
          }
        } else {
          System.out.println("Desktop is not supported");
        }
      }

    } catch (JRException e1) {
      e1.printStackTrace();
    }

  }

}
