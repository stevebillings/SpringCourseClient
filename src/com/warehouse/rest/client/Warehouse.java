package com.warehouse.rest.client;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.warehouse.rest.model.Product;
import com.warehouse.rest.model.ProductList;
import com.warehouse.rest.model.QuantityIncrement;

@Controller
public class Warehouse {
	private static final String STORE_PRODUCTS_URL = "http://localhost:8080/Store/rest/products";
	private RestTemplate rest;

	@Inject
	public void setRest(RestTemplate rest) {
		this.rest = rest;
	}

	// Get all products
	public List<Product> getProducts() {
		System.out.println("******** CLIENT: getProducts");

		ProductList productList = rest.getForObject(STORE_PRODUCTS_URL, ProductList.class);
		return productList.getProducts();
	}

	// Add to a product's quantity
	public void addToProductQuantity(int productId, int additionalQuantity) {
		System.out.println("******** CLIENT: addToProductQuantity");

		QuantityIncrement quantityToAdd = new QuantityIncrement();
		quantityToAdd.setAmount(additionalQuantity);
		rest.put(STORE_PRODUCTS_URL + "/" + productId, quantityToAdd);
	}
}
