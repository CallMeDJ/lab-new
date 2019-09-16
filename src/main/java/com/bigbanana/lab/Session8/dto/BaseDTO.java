package com.bigbanana.lab.Session8.dto;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


public class BaseDTO {
	private Long id;

	private Map<String,String> feature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getFeature() {
		return feature;
	}

	public void setFeature(Map<String, String> feature) {
		this.feature = feature;
	}

	public void putFreature(String key,String value){
		if(feature == null){
			feature = Maps.newConcurrentMap();
		}
		feature.put(key,value);
	}

	public String getFreature(String key){
		if(feature == null){
			return null;
		}

		return feature.get(key);
	}
}
