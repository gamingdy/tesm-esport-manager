package dao;

import java.util.List;

import modele.Arbitre;

public interface IConnexion<T, T1>{

	
	public boolean createTable(); 

	
	public boolean dropTable();

	
	public List<T> getAll() throws Exception;

	
	public List<T> getById(T1... id) throws Exception;

	
	public boolean add(T value) throws Exception;


	public boolean update(T value) throws Exception;

	
	public boolean delete(T1 value) throws Exception;
	

}
