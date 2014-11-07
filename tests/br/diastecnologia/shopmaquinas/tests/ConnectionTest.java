package br.diastecnologia.shopmaquinas.tests;

import br.diastecnologia.shopmaquinas.bean.Message;
import br.diastecnologia.shopmaquinas.bean.User;
import br.diastecnologia.shopmaquinas.dao.Dao;


public class ConnectionTest {
	
	public static void main(String[] args){
		
		Dao dao = new Dao();
		User user = dao.users().where( u-> u.getUserID() == 1 ).findFirst().get();
		
	}
	
	
}
