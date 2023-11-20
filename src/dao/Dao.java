package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import modele.Arbitre;

public interface Dao<T, T1>{

	
	public void createTable() throws SQLException ; 

	
	public boolean dropTable() throws SQLException ;

	
	public List<T> getAll() throws Exception;

	
	public T getById(T1... id) throws Exception;

	
	public boolean add(T value) throws Exception;


	public boolean update(T value) throws Exception;

	
	public boolean delete(T1... value) throws Exception;
	

}
