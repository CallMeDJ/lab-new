package com.bigbanana.lab.Session8.service;

import com.bigbanana.lab.Session8.request.AddCartRequest;
import com.bigbanana.lab.base.ResultDTO;
import org.springframework.stereotype.Service;


public interface CartService {

	ResultDTO<Void> addCart(AddCartRequest addCartRequest);

}
