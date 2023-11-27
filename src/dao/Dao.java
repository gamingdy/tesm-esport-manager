package dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, T1>{

	
	public List<T> getAll() throws Exception;

	
	public T getById(T1... id) throws Exception;

	
	public boolean add(T value) throws Exception;


	public boolean update(T value) throws Exception;

	
	public boolean delete(T1... value) throws Exception;
	

}
