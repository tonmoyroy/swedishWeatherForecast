package swforecast.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import swforecast.dao.UserDao;
import swforecast.entities.User;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	SessionFactory sessionFactory;

	public List<User> list(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getEmail() + "  " + user.getPassword());
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from User where email= :email and password= :password");
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		List<User> ul = query.list();
		System.out.println(ul.size());
		return ul;
	}

	public boolean saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		System.out.println(user.getEmail() + "  " + user.getPassword());
		boolean status = false;
//		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Date date = new Date();
			date.setTime(timestamp.getTime());
			long userid = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));
			user.setUserid(userid);
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			status = true;
//		} catch (Exception e) {
//		}
		return status;
	}
}
