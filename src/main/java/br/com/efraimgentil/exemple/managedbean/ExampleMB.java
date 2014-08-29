package br.com.efraimgentil.exemple.managedbean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.efraimgentil.exemple.business.ExampleBS;
import br.com.efraimgentil.exemple.domain.Example;
import br.com.efraimgentil.exemple.util.MyEntityManagerFactory;

@ManagedBean
@ViewScoped
public class ExampleMB implements Serializable{
	
	private static final long serialVersionUID = -6801520785313969421L;

	private ExampleBS exampleBS;
	
	private List<Example> listExamples;
	
	public ExampleMB() {	}
	
	public ExampleMB(ExampleBS exampleBS) {
		super();
		this.exampleBS = exampleBS;
	}

	@PostConstruct
	public void init(){
		exampleBS = new ExampleBS();
		prepareList();
	}
	
	public void prepareList(){
		listExamples = exampleBS.findAll();
	}

	public void downloadPdf(){
		try {
			JRDataSource beanDataSource = new JRBeanCollectionDataSource( listExamples );
			JasperPrint jasperPrint = JasperFillManager.fillReport( getReportInputStream() , getParameters() , beanDataSource );
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse httpServletResponse =  (HttpServletResponse) facesContext.getExternalContext().getResponse();  
		    httpServletResponse.addHeader("Content-disposition", "attachment; filename=example.pdf");  
			OutputStream outputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			facesContext.responseComplete();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openPdf(){
		try {
			JRDataSource beanDataSource = new JRBeanCollectionDataSource( listExamples );
			JasperPrint jasperPrint = JasperFillManager.fillReport( getReportInputStream() , getParameters() , beanDataSource );
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletResponse httpServletResponse =  (HttpServletResponse) facesContext.getExternalContext().getResponse();  
			OutputStream outputStream = facesContext.getExternalContext().getResponseOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			facesContext.responseComplete();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public StreamedContent openPdfWithPrimefaces() throws JRException{
	  
	  ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	  JRDataSource beanDataSource = new JRBeanCollectionDataSource( listExamples );
      JasperPrint jasperPrint = JasperFillManager.fillReport( getReportInputStream() , getParameters() , beanDataSource );
      JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
      InputStream relatorio = new ByteArrayInputStream(outStream.toByteArray());
	  return new DefaultStreamedContent(relatorio , "application/pdf", "reportfile.pdf" );
	}
	
	protected Map<String, Object> getParameters(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TITLE", "This is MY title");
		return parameters;
	}
	
	protected InputStream getReportInputStream(){
		return this.getClass().getResourceAsStream("/reports/example.jasper");
	}
	
	public List<Example> getListExamples() {
		return listExamples;
	}

	public void setListExamples(List<Example> listExamples) {
		this.listExamples = listExamples;
	}
	
	@PreDestroy
	public void destroy(){
		MyEntityManagerFactory.closeEntityManager( exampleBS.getEntityManager() );
	}

}
