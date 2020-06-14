package com.nishant.ecommerce.controller;

import java.util.Optional;

/**
 * Product
 */

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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/products")
@Api(value = "onlinestore", description = "Operations pertaining to products in online store")
public class ProductController {

	private ProductService productService;

	@Autowired
	ProductRepository productRepository;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "View a list of available products", response = Iterable.class)

	/*@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })*/
	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<Product> getProducts() {
		return productService.getAllProducts();
	}

	@ApiOperation(value = "Add a product")
	@PostMapping(path = "/addProduct", consumes = { "application/json" })
	public Product SaveProduct(@RequestBody Product product) {

		productRepository.save(product);
		return product;

	}

	@ApiOperation(value = "Update a product")
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

	@ApiOperation(value = "Delete a product")
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {

		productRepository.deleteById(id);
		return "Deleted";
	}

	@ApiOperation(value = "Search a product with an ID", response = Product.class)
	@GetMapping("/showProduct/{id}")
	public Optional<Product> GetProductById(@PathVariable("id") Long id) {

		return productRepository.findById(id);
	}
}
