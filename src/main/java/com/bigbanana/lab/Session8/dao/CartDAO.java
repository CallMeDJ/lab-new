package com.bigbanana.lab.Session8.dao;

import com.bigbanana.lab.Session8.dto.BaseDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CartDAO extends BaseDAO{

	@Override
	public String getIdString(Long id) {
		return "CART"+id;
	}
}
