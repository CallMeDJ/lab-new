package com.bigbanana.lab.Session8.dto;

import javafx.util.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
public class TradeDTO extends BaseDTO{
	private List<Pair<Long,Integer>> items;
	private PayStatus payStatus;
	private List<String> PayOrders;
	private TradeStatus tradeStatus;
	private DeliverStatus deliverStatus;
	private List<String> deliverOrders;
}