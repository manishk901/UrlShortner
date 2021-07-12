package com.learning.urlshortner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.urlshortner.model.TinyUrl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UrlShortnerApplication.class)
@WebAppConfiguration
public class UrlShortnetControllerTest {

	protected MockMvc mvc;
	   @Autowired
	   WebApplicationContext webApplicationContext;

	   protected void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	   
	   protected String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
		   protected <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
	   
	   @Test
	   public void createUrl_Test() throws Exception {
		   setUp();
		   String uri="/tinyurl?url=www.google.in";
		   MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		   int status=mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		   assertNotNull(mapFromJson(mvcResult.getResponse().getContentAsString(), TinyUrl.class));
	   }
	   
	   @Test
	   public void createUrlShouldReturnSameCode_Test() throws Exception {
		   setUp();
		   String uri="/tinyurl?url=www.google.com";
		   MvcResult mvcResult1=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		   int status=mvcResult1.getResponse().getStatus();
		   assertEquals(201, status);
		   assertNotNull(mapFromJson(mvcResult1.getResponse().getContentAsString(), TinyUrl.class));
		   
		   MvcResult mvcResult2=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		   int status2=mvcResult2.getResponse().getStatus();
		   assertEquals(208, status2);
		   assertNotNull(mapFromJson(mvcResult1.getResponse().getContentAsString(), TinyUrl.class));
		   assertEquals(mvcResult2.getResponse().getContentAsString(), mvcResult2.getResponse().getContentAsString());
	   }
	   
	   @Test
	   public void getUrlShouldReturnUrlCode_Test() throws Exception {
		   setUp();
		   String uri="/tinyurl?url=www.yahoo.com";
		   MvcResult mvcResult1=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		   TinyUrl tinyUrl=mapFromJson(mvcResult1.getResponse().getContentAsString(), TinyUrl.class);
		   
		   String redirectUrl="/tinyurl/redirect?tinUrl="+tinyUrl.getShortHand();
		   MvcResult mvcResult2=mvc.perform(MockMvcRequestBuilders.get(redirectUrl).accept(MediaType.APPLICATION_JSON)).andReturn();
		   int status=mvcResult2.getResponse().getStatus();
		   assertEquals(200, status);
		   assertNotNull(mvcResult2.getResponse().getContentAsString());
	   }
	   
	   @Test
	   public void getUrlShouldBlankAnd400Code_Test() throws Exception {
		   setUp();
		   String redirectUrl="/tinyurl/redirect?tinUrl=dgdgz";
		   MvcResult mvcResult2=mvc.perform(MockMvcRequestBuilders.get(redirectUrl).accept(MediaType.APPLICATION_JSON)).andReturn();
		   int status=mvcResult2.getResponse().getStatus();
		   assertEquals(400, status);
		   assertEquals("",mvcResult2.getResponse().getContentAsString());
		   
		 
	   }
	   
	   @Test
	   public void getAllTinyUrl_Test() throws Exception {
		   setUp();
		   String uri="/tinyurl?url=www.icc.com";
		   mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		   String url="/tinyurl/getall";
		   MvcResult mvcResult1=mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();
		   assertNotNull(mvcResult1.getResponse().getContentAsString());
	   }
	   
	   

}


