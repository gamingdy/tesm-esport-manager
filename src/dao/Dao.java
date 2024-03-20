package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, T1>{


	List<T> getAll() throws Exception;

	
	Optional<T> getById(T1[] id) throws Exception;

	
	boolean add(T value) throws Exception;


	boolean update(T value) throws Exception;

	
	boolean delete(T1[] value) throws Exception;
	
	
	String visualizeTable() throws Exception ;

}
