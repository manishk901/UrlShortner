package com.learning.urlshortner.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.urlshortner.model.TinyUrl;
@Service
public class TinyUrlService {

	@Autowired
	private TinyUrlRepository repository;

	public TinyUrl createTinyUrl(TinyUrl tinyUrl) {
		return repository.save(tinyUrl);
	}
	
	public TinyUrl findByTinyUrl(String tinyUrl) {
		TinyUrl url=repository.getByTinyUrl(tinyUrl);
		if(url!=null) {
			url.setUsed(url.getUsed()+1);
			repository.save(url);
			return url;
		}else {
			return new TinyUrl();
		}
	}
	
	public TinyUrl findByUrl(String url) {
		TinyUrl tinyUrl=repository.getByUrl(url);
		if(url!=null) {
			
			return tinyUrl;
		}else {
			return new TinyUrl();
		}
	}
	
	public List<TinyUrl> getAll(){
		return repository.findAll();
	}
}
