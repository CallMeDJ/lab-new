package com.bigbanana.lab.Session8.dao;


import org.springframework.stereotype.Component;

@Component
public class TradeDAO extends BaseDAO {
	@Override
	public String getIdString(Long id) {
		return "TRADE"+id;
	}
}
