package com.bigbanana.lab.Session8.dto;

import javafx.util.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CartDTO extends BaseDTO{
	private List<Pair<Long,Integer>> items;
}