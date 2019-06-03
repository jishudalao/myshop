package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 鏉冮檺鎷︽埅鍣�:
 * @author 浼犳櫤.閮槈
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest()
				.getSession().getAttribute("existAdminUser");
		if(adminUser != null){
			
			return actionInvocation.invoke();
		}else{
			
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没登录");
			return ActionSupport.LOGIN;
		}
	}

}
