package com.example.mycatalog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mycatalog.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{
	
  public Page<Product> findByDesignationContains(String kw, Pageable pageable);
  
  @Query("select p from Product p where p.designation like :x and p.price>:y")
  public Page<Product> search(@Param("x")String kw, @Param("y")double minPrice, Pageable pageable);
  
}
