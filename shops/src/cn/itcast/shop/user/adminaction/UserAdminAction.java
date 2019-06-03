package cn.itcast.shop.user.adminaction;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAdminAction extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();

	public User getModel() {
		return user;
	}
	
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	
	public String delete(){
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	
	
	public String edit(){
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}
	
	
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
}
