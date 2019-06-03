package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageHibernateCallback;


public class UserDao extends HibernateDaoSupport {

	
	public User findByUsername(String username) {
		String hql = "from User where username = ?0";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	
	public User findByCode(String code) {
		String hql = "from User where code = ?0";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, code);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	
	public User login(User user) {
		String hql = "from User where username = ?0 and password = ?1 and state = ?2";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql,
				user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null, begin, limit));
		return list;
	}

	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
}
