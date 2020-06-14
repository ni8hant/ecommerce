package com.nishant.ecommerce.controller;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.ecommerce.model.Product;
import com.nishant.ecommerce.repository.ProductRepository;
import com.nishant.ecommerce.service.ProductService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/products")
@Api
public class ProductController {

	private ProductService productService;

	@Autowired
	ProductRepository productRepository;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<Product> getProducts() {
		return productService.getAllProducts();
	}

	@PostMapping(path = "/addProduct", consumes = { "application/json" })
	public Product SaveProduct(@RequestBody Product product) {

		productRepository.save(product);
		return product;

	}


	@PutMapping("/updateProduct/{id}")
	@ResponseBody
	public String updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {

		Optional<Product> prod = productRepository.findById(id);
		if (!prod.isPresent()) {
			return "Product Not found";
		}
		Product p = prod.get();
		p.setName(product.getName());
		p.setPictureUrl(product.getPictureUrl());
		p.setPrice(product.getPrice());
		p.setStatus(product.getStatus());
		productRepository.save(p);
		return "Product updated Successfully";

	}

	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {

		productRepository.deleteById(id);
		return "Deleted";
	}
}
