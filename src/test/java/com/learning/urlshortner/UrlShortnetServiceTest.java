package com.learning.urlshortner;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.urlshortner.model.TinyUrl;
import com.learning.urlshortner.repository.TinyUrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortnetServiceTest {

	@Autowired
	private TinyUrlService service;
	
	@Test
	public void findByUrl_Test() {
		TinyUrl tinyUrl= new TinyUrl();
		tinyUrl.setShortHand("asfs");
		tinyUrl.setUrl("www.google.uk");
		tinyUrl.setUsed(0);
		service.createTinyUrl(tinyUrl);
		TinyUrl tinyUrl2=service.findByUrl("www.google.uk");
		assertEquals(tinyUrl.getShortHand(), tinyUrl2.getShortHand());
		
	}
	
	@Test
	public void findByUrlShouldReturnNull_Test() {
		TinyUrl tinyUrl= new TinyUrl();
		tinyUrl.setShortHand("asfs");
		tinyUrl.setUrl("www.google.uk");
		tinyUrl.setUsed(0);
		service.createTinyUrl(tinyUrl);
		TinyUrl tinyUrl2=service.findByUrl("www.google.us");
		assertNull(tinyUrl2);
		
	}

}
