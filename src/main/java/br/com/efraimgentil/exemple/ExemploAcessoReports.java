package br.com.efraimgentil.exemple;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.URL;

public class ExemploAcessoReports {

	public static void main(String[] args) {
		
		//Recupera o relatorio como um stream
		InputStream is = ExemploAcessoReports.class.getResourceAsStream("/reports/exemplo.jasper");
		try {
			System.out.println( is.available() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Recupera o path direto do relatorio
		URL url = ExemploAcessoReports.class.getResource("/reports/exemplo.jasper");
		System.out.println( url.getPath() );
		
	}
	
}
