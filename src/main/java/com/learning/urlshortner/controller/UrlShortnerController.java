package com.learning.urlshortner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.urlshortner.model.TinyUrl;
import com.learning.urlshortner.repository.TinyUrlService;
import com.learning.urlshortner.util.Base62Util;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tinyurl")
public class UrlShortnerController {

	@Autowired
	private TinyUrlService tinyUrlService;

	@ApiOperation(value = "shorten the url", response = TinyUrl.class)
	@GetMapping
	public ResponseEntity<TinyUrl> createTinyUrl(@ApiParam(value = "The Url to be shorten") @RequestParam String url) {
		TinyUrl tinyUrl = tinyUrlService.findByUrl(url);
		if (tinyUrl != null && tinyUrl.getShortHand() != null) {
			return new ResponseEntity<TinyUrl>(tinyUrl, HttpStatus.ALREADY_REPORTED);

		} else {
//			final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//			SecureRandom random = new SecureRandom();
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < 6; i++) {
//				int randomIndex = random.nextInt(chars.length());
//				sb.append(chars.charAt(randomIndex));
//			}
			String str = Base62Util.encode(url);
			TinyUrl newTinyUrl = new TinyUrl();
			newTinyUrl.setShortHand(str);
			newTinyUrl.setUrl(url);
			newTinyUrl.setUsed(0);
			tinyUrlService.createTinyUrl(newTinyUrl);
			return new ResponseEntity<TinyUrl>(newTinyUrl, HttpStatus.CREATED);
		}

	}

	@ApiOperation(value = "Get the url", response = TinyUrl.class)
	@GetMapping("/redirect")
	public ResponseEntity<String> getTinyUrl(@ApiParam(value = "The Url to be retriveid") @RequestParam String tinUrl) {
		TinyUrl tinyUrl = tinyUrlService.findByTinyUrl(tinUrl);
		if (tinyUrl != null && tinyUrl.getUrl() != null) {
			return new ResponseEntity<String>(tinyUrl.getUrl(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value="Get all tinyUrls", response = TinyUrl.class)
	@GetMapping("/getall")
	public ResponseEntity<List<TinyUrl>> getAll() {
		List<TinyUrl> tinyUrls = tinyUrlService.getAll();
		if (tinyUrls.size() > 0) {
			return new ResponseEntity<List<TinyUrl>>(tinyUrls, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TinyUrl>>(new ArrayList<TinyUrl>(), HttpStatus.OK);
		}
	}

}
