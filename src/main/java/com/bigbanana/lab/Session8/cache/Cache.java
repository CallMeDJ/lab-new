package com.bigbanana.lab.Session8.cache;

import com.google.common.collect.Maps;

import java.util.Map;


public class Cache {
	public static final Map<String,Object> cache = Maps.newConcurrentMap();

}
