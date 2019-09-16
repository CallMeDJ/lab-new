package com.bigbanana.lab.Session5;

public class BananaHashMap<K,V> extends BananaMap<K,V> {
	@Override
	int size() {
		return 0;
	}

	@Override
	boolean isEmpty() {
		return false;
	}

	@Override
	boolean containsKey(K key) {
		return false;
	}

	@Override
	V get(K key) {
		return null;
	}

	@Override
	V put(K key, V value) {
		return null;
	}

	@Override
	V remove(K key) {
		return null;
	}
}
