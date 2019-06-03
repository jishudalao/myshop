package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart implements Serializable{
	
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	

	private double total;

	public double getTotal() {
		return total;
	}

	
	public void addCart(CartItem cartItem) {
		
		Integer pid = cartItem.getProduct().getPid();
		
		if(map.containsKey(pid)){
			
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			
			map.put(pid, cartItem);
		}
		
		total += cartItem.getSubtotal();
	}

	
	public void removeCart(Integer pid) {
		
		CartItem cartItem = map.remove(pid);
		
		total -= cartItem.getSubtotal();
	}

	// 3.娓呯┖璐墿杞�
	public void clearCart() {
		
		map.clear();
		
		total = 0;
	}
}
