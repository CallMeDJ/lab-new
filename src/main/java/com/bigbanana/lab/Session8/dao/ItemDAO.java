package com.bigbanana.lab.Session8.dao;


import org.springframework.stereotype.Component;

@Component
public class ItemDAO extends BaseDAO{

	@Override
	public String getIdString(Long id) {
		return "ITEM"+id;
	}
}
