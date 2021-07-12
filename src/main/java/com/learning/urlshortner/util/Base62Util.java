package com.learning.urlshortner.util;

import java.util.HashMap;
import java.util.Map;

public class Base62Util {
	static Long counter = 1L;
	static Map<Long, String> indexToUrl = new HashMap<Long, String>();
	static Map<String, Long> urlToIndex = new HashMap<String, Long>();
	static String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public  static String encode(String longUrl) {
	    if (urlToIndex.containsKey(longUrl)) {
	        return base62Encode(urlToIndex.get(longUrl));
	    }
	    else {
	        indexToUrl.put(counter, longUrl);
	        urlToIndex.put(longUrl, counter);
	        counter++;
	        return base62Encode(urlToIndex.get(longUrl));
	    }
	}

	public String decode(String shortUrl) {
	    String base62Encoded = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
	    long decode = 0;
	    for(int i = 0; i < base62Encoded.length(); i++) {
	        decode = decode * 62 + base62.indexOf("" + base62Encoded.charAt(i));
	    }
	    return indexToUrl.get(decode);
	}

	private static String base62Encode(long value) {
	    StringBuilder sb = new StringBuilder();
	    while (value != 0) {
	        sb.append(base62.charAt((int)(value % 62)));
	        value /= 62;
	    }
	    while (sb.length() < 7) {
	        sb.append(0);
	    }
	    return sb.reverse().toString();
	}

}
