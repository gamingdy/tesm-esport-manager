package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, T1>{

	
	public List<T> getAll() throws Exception;

	
	public Optional<T> getById(T1... id) throws Exception;

	
	public boolean add(T value) throws Exception;


	public boolean update(T value) throws Exception;

	
	public boolean delete(T1... value) throws Exception;
	
	
	public String visualizeTable() throws Exception ;

}
