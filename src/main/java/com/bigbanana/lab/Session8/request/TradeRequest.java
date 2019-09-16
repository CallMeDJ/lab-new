package com.bigbanana.lab.Session8.request;

import com.bigbanana.lab.Session8.dto.CartDTO;
import com.bigbanana.lab.Session8.dto.TradeDTO;
import lombok.Data;

@Data
public class TradeRequest extends BaseRequest {
	private CartDTO cartDTO;
	private TradeDTO tradeDTO;

	public CartDTO getCartDTO() {
		return cartDTO;
	}

	public void setCartDTO(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}

	public TradeDTO getTradeDTO() {
		return tradeDTO;
	}

	public void setTradeDTO(TradeDTO tradeDTO) {
		this.tradeDTO = tradeDTO;
	}
}
