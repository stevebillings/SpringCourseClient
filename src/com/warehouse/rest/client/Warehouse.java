package com.warehouse.rest.client;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.warehouse.rest.model.Product;
import com.warehouse.rest.model.ProductList;

@Controller
public class Warehouse {
	private RestTemplate rest;

	@Inject
	public void setRest(RestTemplate rest) {
		this.rest = rest;
	}

	// Get all products
	public List<Product> getProducts() {
		System.out.println("******** CLIENT: getProducts");

		ProductList productList = rest.getForObject("http://localhost:8080/Store/rest/products", ProductList.class);
		return productList.getProducts();
	}
}
