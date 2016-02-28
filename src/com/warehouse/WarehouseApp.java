package com.warehouse;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.warehouse.rest.client.Warehouse;
import com.warehouse.rest.model.Product;

public class WarehouseApp {

	public static void main(String[] args) throws Exception {
		WarehouseApp app = new WarehouseApp();
		app.run();
	}

	public void run() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/WarehouseContext.xml");
		Warehouse client = (Warehouse) context.getBean("warehouse");

		// Get products
		System.out.println("\n==== Get Products ...");
		List<Product> products = client.getProducts();
		System.out.println("---- Total products=" + products.size());
		for (int i = 0; i < products.size(); i++) {
			System.out.println("product[" + i + "]=" + products.get(i));
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
