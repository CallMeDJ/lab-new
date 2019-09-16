package com.bigbanana.lab.Session8.service;

import com.bigbanana.lab.Session8.dto.CartDTO;
import com.bigbanana.lab.Session8.dto.DeliverStatus;
import com.bigbanana.lab.Session8.dto.PayStatus;
import com.bigbanana.lab.Session8.dto.TradeDTO;
import com.bigbanana.lab.Session8.request.TradeRequest;
import com.bigbanana.lab.base.ResultDTO;
import org.springframework.stereotype.Service;

public interface TradeService {

//	下单、支付、发货、收货确认完成。请实现这些所有服务

	ResultDTO<Long> createTrade(TradeRequest tradeRequest);

	ResultDTO<PayStatus> pay(TradeRequest tradeRequest);

	ResultDTO<DeliverStatus> deliver(TradeRequest tradeRequest);

	ResultDTO<Void> done(TradeRequest tradeRequest);

	ResultDTO<TradeDTO> get(TradeRequest tradeRequest);

}
