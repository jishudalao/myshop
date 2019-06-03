package cn.itcast.shop.product.action;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	
	private Product product = new Product();
	
	private ProductService productService;
	
	private Integer cid;
	
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	
	private CategoryService categoryService;
	
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}

	
	public String findByPid() {
		
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	
	public String findByCid() {
		
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	
	public String findByCsid() {
		
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
