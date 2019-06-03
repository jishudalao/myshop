package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionSupport;


public class CartAction extends ActionSupport {
	
	private Integer pid;
	
	private Integer count;
	
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	public String addCart() {
		
		CartItem cartItem = new CartItem();
		
		cartItem.setCount(count);
		
		Product product = productService.findByPid(pid);
		
		cartItem.setProduct(product);
		
		Cart cart = getCart();
		cart.addCart(cartItem);

		return "addCart";
	}

	
	public String clearCart(){
		
		Cart cart = getCart();
		
		cart.clearCart();
		return "clearCart";
	}
	
	
	public String removeCart(){
		
		Cart cart = getCart();
		
		cart.removeCart(pid);
		
		return "removeCart";
	}
	
	
	public String myCart(){
		return "myCart";
	}
	
	
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession()
					.setAttribute("cart", cart);
		}
		return cart;
	}
}
