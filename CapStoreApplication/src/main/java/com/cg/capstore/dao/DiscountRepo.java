package com.cg.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.capstore.beans.ProductBean;
@Repository
public interface DiscountRepo extends JpaRepository<ProductBean, String> {
	
	@Query("select p from ProductBean p where p.productId=(:pid)")
	public ProductBean getProduct(@Param(value="pid") String pid);

}
