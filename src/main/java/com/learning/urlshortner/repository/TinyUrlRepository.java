package com.learning.urlshortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learning.urlshortner.model.TinyUrl;

public interface TinyUrlRepository extends JpaRepository<TinyUrl,Integer> {

	@Query("Select t from TinyUrl t where t.shortHand=:tinyUrl")
	public TinyUrl getByTinyUrl(String tinyUrl);
	
	@Query("Select t from TinyUrl t where t.url=:url")
	public TinyUrl getByUrl(String url);
}
