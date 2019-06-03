package cn.itcast.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.MailUitls;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.UUIDUtils;


@Transactional
public class UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	
	public void save(User user) {
	
		user.setState(1); 
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		MailUitls.sendMail(user.getEmail(), code);
	}

	
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	
	public void update(User existUser) {
		userDao.update(existUser);
	}


	public User login(User user) {
		return userDao.login(user);
	}

	
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		
		pageBean.setPage(page);
		
		int limit = 5;
		pageBean.setLimit(limit);
		
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}


	public void delete(User existUser) {
		userDao.delete(existUser);
	}
}
