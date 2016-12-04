package br.com.ajudantes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormataData {
	
	/*
	 * FONTE
	 * http://www.guj.com.br/t/converter-string-para-date/101891/4
	 * acesso 04/12/2016
	 * 
	 * */
	public static java.sql.Date formataData(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
	
	public static String formataDataBr(Date dataBanco) {
		/*
		 * FONTE
		 * http://javafxportal.blogspot.com.br/2012/03/date-format-example.html
		 * acesso 04/12/2016
		 * 
		 * */
		Format formatarData = new SimpleDateFormat("dd/MM/yyyy");
		String dataBr = formatarData.format(dataBanco);
		return dataBr;
	}
	
}
