package swforecast.dao;

import java.util.List;

import swforecast.entities.User;


public interface UserDao {
	public List<User> list(User user);

	public boolean saveOrUpdate(User user);
}
