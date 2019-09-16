package com.bigbanana.lab.Session8.cache;


import org.springframework.stereotype.Component;

@Component
public class ItemCache extends BaseCache {

	@Override
	public String getIdString(Long id) {
		return "ITEM"+id;
	}
}
