package com.warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.warehouse.rest.client.Warehouse;
import com.warehouse.rest.model.Product;

public class WarehouseApp {

	private static final int PRODUCT_QUANTITY_INCREMENT = 100;

	public static void main(String[] args) throws Exception {
		WarehouseApp app = new WarehouseApp();
		app.run();
	}

	public void run() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/WarehouseContext.xml");
		Warehouse client = (Warehouse) context.getBean("warehouse");

		Map<String, Integer> origQuantities = new HashMap<>();

		// Get products
		System.out.println("\n==== Get Products ...");
		List<Product> products = client.getProducts();
		System.out.println("---- Total products=" + products.size());
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			System.out.println("product[" + i + "]=" + product);
			origQuantities.put(product.getProductName(), product.getAvailableQuantity());

			// Add to the quantity
			client.addToProductQuantity(product.getProductId(), PRODUCT_QUANTITY_INCREMENT);
		}

		// Get the now-re-stocked products
		System.out.println("\n==== Get Products after restocking...");
		products = client.getProducts();
		System.out.println("---- Total products=" + products.size());
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			System.out.println("product[" + i + "]=" + product);
			int expectedQuantity = origQuantities.get(product.getProductName()) + PRODUCT_QUANTITY_INCREMENT;
			int actualQuantity = product.getAvailableQuantity();
			if (actualQuantity == expectedQuantity) {
				System.out.println(product.getProductName() + ": New quantity (" + actualQuantity + ") is correct");
			} else {
				System.out.println("\n\n\nERROR: " + product.getProductName() + ": Expected quantity "
						+ expectedQuantity + ", but got: " + actualQuantity);
			}
		}

		// Get products by id
		// System.out.println("\n==== Get Products by Id ...");
		// System.out.println("---- [id=" + products.get(0).getProductId() + "]
		// "
		// + client.getProduct(products.get(0).getProductId()));
		// System.out.println("---- [id=" + products.get(1).getProductId() + "]
		// "
		// + client.getProduct(products.get(1).getProductId()));

		// Add a new product
		// System.out.println("\n==== Add new Product ...");
		// Product product = new Product();
		// product.setProductName("Finance");
		// client.createProduct(product);
		//
		// products = client.getProducts();
		// for (int i=0; i<products.size(); i++) {
		// System.out.println("product[" + i + "]=" + products.get(i));
		// }

		// Update a product
		// System.out.println("\n==== Update a Product ...");
		// product = products.get(products.size()-1);
		// product.setProductName(product.getProductName() + "-2");
		// client.updateProduct(product);
		//
		// products = client.getProducts();
		// for (int i=0; i<products.size(); i++) {
		// System.out.println("product[" + i + "]=" + products.get(i));
		// }

		// Delete a product
		// System.out.println("\n==== Delete a Product ...");
		// product = products.get(products.size()-1);
		// client.deleteProduct(product);
		//
		// products = client.getProducts();
		// for (int i=0; i<products.size(); i++) {
		// System.out.println("product[" + i + "]=" + products.get(i));
		// }
	}
}
