package com.bigbanana.lab.Session8.request;

import lombok.Data;

@Data
public class AddCartRequest extends BaseRequest{
	Long cartId;

	Long itemId;

	Integer count;
}
