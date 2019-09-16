package com.bigbanana.lab.Session8.service.impl;

import com.bigbanana.lab.Session8.dto.DeliverStatus;
import com.bigbanana.lab.Session8.dto.PayStatus;
import com.bigbanana.lab.Session8.dto.TradeDTO;
import com.bigbanana.lab.Session8.request.TradeRequest;
import com.bigbanana.lab.Session8.service.TradeService;
import com.bigbanana.lab.base.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService{

//	下单、支付、发货、收货确认完成。请实现这些所有服务

	@Override
	public ResultDTO<Long> createTrade(TradeRequest tradeRequest){

		return null;
	}

	@Override
	public ResultDTO<PayStatus> pay(TradeRequest tradeRequest){

		return null;
	}


	@Override
	public ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest){

		return null;
	}

	@Override
	public ResultDTO<Void> done(TradeRequest tradeRequest){


		return null;
	}

	@Override
	public ResultDTO<TradeDTO> get(TradeRequest tradeRequest) {


		return null;
	}


}
