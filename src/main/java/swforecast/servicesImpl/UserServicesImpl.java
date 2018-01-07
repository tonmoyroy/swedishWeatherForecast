package swforecast.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swforecast.dao.UserDao;
import swforecast.entities.User;
import swforecast.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{
	@Autowired
	UserDao userDao;

	public List<User> list(User user) {
		// TODO Auto-generated method stub
		return userDao.list(user);
	}

	public boolean saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		return userDao.saveOrUpdate(user);
	}
}
