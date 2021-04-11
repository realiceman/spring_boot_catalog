package com.example.mycatalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.mycatalog.dao.IProductRepository;
import com.example.mycatalog.entities.Product;

@SpringBootApplication
public class MyCatalogApplication  implements CommandLineRunner{
    @Autowired
	private IProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		  productRepo.save(new Product(null,"Ordi hp",1500,20)); productRepo.save(new
		  Product(null,"Ordi lenovo",2500,220)); productRepo.save(new
		  Product(null,"scanner hp",500,30)); productRepo.save(new
		  Product(null,"Smartphone Samsung",1500,50)); Page<Product>
		  products=productRepo.findByDesignationContains("h",PageRequest.of(0, 2));
		  products.getContent().forEach(p -> { System.out.println(p.toString()); });
		  
		  System.out.println("------------------------------");
		  
		  Page<Product> products2=productRepo.search("%ph%",20,PageRequest.of(0, 2));
		  products2.getContent().forEach(p -> { System.out.println(p.toString()); });
		 
	}

}
