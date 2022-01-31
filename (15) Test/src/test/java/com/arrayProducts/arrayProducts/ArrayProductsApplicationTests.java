package com.arrayProducts.arrayProducts;

import com.arrayProducts.arrayProducts.module.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArrayProductsApplicationTests {

	static Logger logger = LogManager.getLogger(ArrayProductsApplicationTests.class);

	private String url;
	private ObjectMapper mapper = new ObjectMapper();
	private static long start = System.nanoTime();

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void urlMethod(){
		url = String.format("http://localhost:%d/api/", port);
	}

	@BeforeEach
	public void antes(){
		logger.info("Inicio del test");
	}

	@AfterAll
	public static void despues(){
		logger.info("Final del test");
	}

	@BeforeAll
	public static void despuesGeneral(){
		logger.info("Finalizó la ejecución de los tests. La misma duró {} ms", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
	}

	@Test
	public void obtenerTodosLosProductos() {
		String urlTest = url + "productos";
		List<Product> productosResult = this.restTemplate.getForObject(urlTest, List.class);
		Assert.notNull(productosResult, "Null - Test 1");
		Assert.notEmpty(productosResult, "Empty - Test 1");
	}

	@Test
	public void obtenerProductoPorId() {
		String urlTest = url + "productos/0";
		Product productoResult = this.restTemplate.getForObject(urlTest, Product.class);
		Assert.notNull(productoResult, "Null - Test 2");
		Assert.isTrue(productoResult.getId() == 0, "ID error - Test 2");
		Assert.isTrue(productoResult.getTitle().equals("Harina"), "Name error - Test 2");
	}

	@Test
	public void crearProducto() {
		String urlTest = url + "productos";
		Product productoPost = this.restTemplate.postForObject(urlTest, (new Product("Leche", (Integer)10, (Integer)0)) , Product.class);
		Assert.notNull(productoPost, "Null - Test 3");
		logger.info(productoPost.getId());
		Assert.isTrue(productoPost.getId() == 2, "ID error - Test 3 {}");
		Assert.isTrue(productoPost.getTitle().equals("Leche"), "Name error - Test 3");
	}

	@Test
	public void obtenerTodosLosProductosHttp() throws IOException {
		String urlTest = url + "productos";

		HttpUriRequest request = new HttpGet(urlTest);

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		String content = EntityUtils.toString((httpResponse.getEntity()));

		List<Product> productosResult = mapper.readValue(content, List.class);

		Assert.notNull(productosResult, "Null list - Test 4");
		Assert.notEmpty(productosResult, "Empty list - Test 4");
	}

	@Test
	public void obtenerProductoPorIdHttp() throws IOException {
		String urlTest = url + "productos/0";

		HttpUriRequest request = new HttpGet(urlTest);

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		String content = EntityUtils.toString((httpResponse.getEntity()));

		Product productoResult = mapper.readValue(content, Product.class);

		Assert.notNull(productoResult, "Null product - Test 5");
		Assert.isTrue(productoResult.getId() == 0, "ID error - Test 5");
		Assert.isTrue(productoResult.getTitle().equals("Harina"), "Name error - Test 5");
	}

}
