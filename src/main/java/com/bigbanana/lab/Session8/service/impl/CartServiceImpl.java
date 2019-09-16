package com.bigbanana.lab.Session8.service.impl;

import com.bigbanana.lab.Session8.request.AddCartRequest;
import com.bigbanana.lab.Session8.service.CartService;
import com.bigbanana.lab.base.ResultDTO;
import com.bigbanana.lab.base.ResultExecutor;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Override
	public ResultDTO<Void> addCart(AddCartRequest addCartRequest){

		return new ResultExecutor<Void>() {
			@Override
			public Void run() {

				System.out.println("cart service");
				return null;
			}
		}.execute();

		
	}

}
