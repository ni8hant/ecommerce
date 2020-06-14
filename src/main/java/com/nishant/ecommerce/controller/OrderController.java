package com.nishant.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.ecommerce.dto.OrderProductDto;
import com.nishant.ecommerce.exception.ResourceNotFoundException;
import com.nishant.ecommerce.model.Order;
import com.nishant.ecommerce.model.OrderStatus;
import com.nishant.ecommerce.model.ProductOrder;
import com.nishant.ecommerce.service.OrderService;
import com.nishant.ecommerce.service.ProductOrderService;
import com.nishant.ecommerce.service.ProductService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/orders")
@Api(value="orders",description ="Data Service operations on place an Order",tags="orders")
public class OrderController {

	ProductService productService;
	OrderService orderService;
	ProductOrderService productOrderService;

	public OrderController(ProductService productService, OrderService orderService,
			ProductOrderService productOrderService) {
		this.productService = productService;
		this.orderService = orderService;
		this.productOrderService = productOrderService;
	}

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> list() {
		return this.orderService.getAllOrders();
	}

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
		validateProductsExistence(formDtos);
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		order = this.orderService.create(order);

		List<ProductOrder> orderProducts = new ArrayList<>();
		for (OrderProductDto dto : formDtos) {
			orderProducts.add(productOrderService.create(
					new ProductOrder(order, productService.getProduct(dto.getProduct().getId()), dto.getQuantity())));
		}

		order.setProductsOrder(orderProducts);
		this.orderService.update(order);

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	}

	private void validateProductsExistence(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found");
		}
	}

	 public static class OrderForm {

	        private List<OrderProductDto> productOrders;

	        public List<OrderProductDto> getProductOrders() {
	            return productOrders;
	        }

	        public void setProductOrders(List<OrderProductDto> productOrders) {
	            this.productOrders = productOrders;
	        }
	    }

}
