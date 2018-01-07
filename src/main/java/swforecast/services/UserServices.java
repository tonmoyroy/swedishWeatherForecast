package swforecast.services;

import java.util.List;

import swforecast.entities.User;


public interface UserServices {
	public List<User> list(User user);

	public boolean saveOrUpdate(User user);
}
