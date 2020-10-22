package Dominio;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.derby.jdbc.EmbeddedDriver;

import Persistencia.Agente;

public class Usuario {
	
	public String mLogin;
	public String mPassword;
	
	
	//Constructor para la creaci—n de un objeto Usuario vacio
	public Usuario(){
		this.mLogin = null;
		this.mPassword = null;
	}
	
	//Constructor para la creaci—n de un Usuario
	public Usuario(String login, String password){
		this.mLogin = login;
		this.mPassword = password;
	}
	
	//Selecci—n de un usuario de la base de datos a partir del login y el password
	@SuppressWarnings("unchecked")
	public  static Usuario read(String login, String password) throws Exception{
		String l,g;
		Usuario u = null;
		String SQL_Consulta = "SELECT login, pass FROM Usuario WHERE login = '"+login+"' AND pass = '"+password+"'";
		Agente agente=Agente.getAgente();
		Vector vectoradevolver = agente.select(SQL_Consulta);
		Vector<Object> aux = new Vector<Object>();
		if (vectoradevolver.size() == 1){
			aux = (Vector<Object>) vectoradevolver.elementAt(0);
			u = new Usuario((String) aux.elementAt(0), (String) aux.elementAt(1));
		}
		return u;
	}
	
	//Inserci—n de un nuevo usuario en la base de datos
	public boolean insert() throws Exception{
		boolean check=false;
		String SQL_Insert="INSERT INTO Usuario VALUES('"+this.mLogin+"','"+this.mPassword+"'";
		Agente agente=Agente.getAgente();
    	int res=agente.insert(SQL_Insert);
    	if (res==1) {
    		check=true;
    	}
		return check;
	}
	
	public boolean delete() throws Exception{
		boolean check=false;
		Agente agente=Agente.getAgente();
		String SQL_delete="DELETE FROM Usuario WHERE login= '"+this.mLogin+"' AND pass= '"+this.mPassword+"'";
    	int res=agente.delete(SQL_delete);
    	if (res==1) {
    		check=true;
    	}
		return check;
	}
	
	/*
	public void showDB () throws Exception{
		Driver derbyEmbeddedDriver = new EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);
		Connection mBD = DriverManager.getConnection(""+BDConstantes.DRIVER+":"+BDConstantes.DBNAME+";create=false", BDConstantes.DBUSER, BDConstantes.DBPASS);
		Statement stmt = mBD.createStatement();
		ResultSet miResultSet = stmt.executeQuery("SELECT * FROM Usuario");
		while(miResultSet.next()) {
			System.out.println(miResultSet.getString("login")+" "+miResultSet.getString("pass"));
		}
		stmt.close();
    	mBD.close();
		}
		*/
	
	
	public int update () throws Exception{
		//por ahora no nos ha hecho falta actualizar nada...
		return 0;
	}
	
	
}
