package com.catalogs.productcatalog.jpa.controller.intergration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.catalogs.productcatalog.ProductCatalogApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductCatalogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CatalogProductControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void A_testGetCatalog()throws Exception {
		String url = "http://localhost:" + port + "/catalogs/MUSIC-REF";

		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass")
				.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expected = "{\"id\":3,\"reference\":\"MUSIC-REF\",\"name\":\"Music\",\"description\":\"Music Catalog\",\"disabled\":false,\"dateExp\":null,\"products\":[{\"id\":6,\"description\":\"Queen. The Platinum Collection: Greatest Hits I, II & III\",\"name\":\"Queen. Greatest Hits\",\"available\":true,\"price\":13.99,\"picture\":\"/CD-queen-greatest-1-2-3.png\",\"reference\":\"QUEEN-GH-123\"},{\"id\":7,\"description\":\"Pink Floyd: The Dark Side of the Moon LP (12\\\" album, 33 rpm)\",\"name\":\"The Dark Side of the Moon LP\",\"available\":true,\"price\":22.99,\"picture\":\"/CD-pinkf-dsm.png\",\"reference\":\"PINK-FD-DSM-5698\"}]}";
		try { 
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void B_testGetProductDetails()throws Exception {
		String url = "http://localhost:" + port + "/products/LISEN-IP-CG-6FT";

		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass")
				.getForEntity(url, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expected = "{\"id\":4,\"description\":\"LISEN iPhone Charger Cable 6ft, [Apple MFi Certified] Lightning Cable, Nylon Braided USB Fast Charging Cord Compatible with iPhone X/Xs Max/XR / 8/8 Plus / 7/7 Plus iPad (Grey)\",\"name\":\"LISEN iPhone Charger Cable 6ft\",\"available\":true,\"price\":15.99,\"picture\":\"/LISEN-iPhone-Charger-6ft.png\",\"reference\":\"LISEN-IP-CG-6FT\"}";
		try { 
			JSONAssert.assertEquals(expected, response.getBody(), true);
		} catch (JSONException e) {
			fail(e.getMessage());
		}
	}



	@Test
	public void C_testAddProductToCart() throws Exception {

		String url = "http://localhost:" + port + "/cart/LISEN-IP-CG-6FT/add";
		
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		
		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass").exchange(url,
																HttpMethod.POST, entity, String.class);

		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}
	
	@Test
	public void D_testGetUserCart() throws Exception {

		String url = "http://localhost:" + port + "/cart/products";
		
		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass")
				.getForEntity(url, String.class);
		

		assertEquals(HttpStatus.OK, response.getStatusCode());

		String expected = "[{\"cartOwner\":{\"id\":1,\"firstname\":\"User1\",\"lastname\":\"User1\",\"phone\":null,\"email\":null,\"login\":\"user1\"},\"product\":{\"id\":4,\"description\":\"LISEN iPhone Charger Cable 6ft, [Apple MFi Certified] Lightning Cable, Nylon Braided USB Fast Charging Cord Compatible with iPhone X/Xs Max/XR / 8/8 Plus / 7/7 Plus iPad (Grey)\",\"name\":\"LISEN iPhone Charger Cable 6ft\",\"available\":true,\"price\":15.99,\"picture\":\"/LISEN-iPhone-Charger-6ft.png\",\"reference\":\"LISEN-IP-CG-6FT\"},\"nbrPieces\":1}]";
		try { 
			JSONAssert.assertEquals(expected, response.getBody(), false);
		} catch (JSONException e) {
			fail(e.getMessage());
		}
		
	}

	
	@Test
	public void E_testRemoveProductFromCart() throws Exception {

		String url = "http://localhost:" + port + "/cart/LISEN-IP-CG-6FT/remove";
		
//		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass")
//				.getForEntity(url, String.class);
		
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		
		ResponseEntity<String> response = restTemplate.withBasicAuth("user1", "pass").exchange(url,
																HttpMethod.POST, entity, String.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}


}
