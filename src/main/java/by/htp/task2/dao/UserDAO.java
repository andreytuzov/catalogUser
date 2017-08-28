package by.htp.task2.dao;

import java.util.List;

import by.htp.task2.entity.User;

public interface UserDAO {

	void add(User user);
	User read(int id);
	List<User> readAll();
	void update(User user);
	void delete(int id);
	void deleteAll(String ids);
	
}
