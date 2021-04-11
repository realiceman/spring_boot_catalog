package com.example.mycatalog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mycatalog.dao.IProductRepository;
import com.example.mycatalog.entities.Product;

@Controller
public class ProductController {
  @Autowired	
  private IProductRepository productRepo;	
	
  @GetMapping(path = "/index")	
  public String index() {
	  return "index";
  }
  
  @GetMapping(path = "/products")	
  public String products(Model model,
		  @RequestParam(name = "page",defaultValue = "0")int page,
		  @RequestParam(name = "size",defaultValue = "5")int size,
		  @RequestParam(name = "kw",defaultValue = "")String kw) {
	  Page<Product> pageProducts=productRepo.findByDesignationContains(kw, PageRequest.of(page, size)); 
	  model.addAttribute("pageProducts",pageProducts);
	  model.addAttribute("currentPage",page);
	  model.addAttribute("size",size);
	  model.addAttribute("kw",kw);
	  model.addAttribute("pages", new int[pageProducts.getTotalPages()]);
	  return "products";
  }
  
  
  @PostMapping(path = "/deleteProduct")	
  public String deleteProduct(Long id, String kw, String page, String size) {
	  productRepo.deleteById(id);
	  return "redirect:/products?page="+page+"&kw="+kw+"&size="+size;
  }
}
