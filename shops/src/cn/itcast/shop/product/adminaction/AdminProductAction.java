package cn.itcast.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	
	private Product product = new Product();

	public Product getModel() {
		return product;
	}

	
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
	
		return "findAll";
	}

	
	public String addPage() {
		
		List<CategorySecond> csList = categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "addPageSuccess";
	}

	
	public String save() throws IOException {
		
		product.setPdate(new Date());
		
		if(upload != null){
			
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			
			File diskFile = new File(path + "//" + uploadFileName);
			
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	
	public String delete() {
		
		product = productService.findByPid(product.getPid());
		
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		
		productService.delete(product);
		
		return "deleteSuccess";
	}

	
	public String edit() {
		
		product = productService.findByPid(product.getPid());
		
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		
		return "editSuccess";
	}

	
	public String update() throws IOException {
		
		product.setPdate(new Date());
		
		
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			
			File diskFile = new File(path + "//" + uploadFileName);
			
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		
		return "updateSuccess";
	}
}
