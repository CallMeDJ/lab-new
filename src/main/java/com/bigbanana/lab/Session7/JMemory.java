package com.bigbanana.lab.Session7;

public class JMemory {
	private int gcCount;
	private boolean isMark;
	private Object object;

	public int getGcCount() {
		return gcCount;
	}

	public void setGcCount(int gcCount) {
		this.gcCount = gcCount;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isMark() {
		return isMark;
	}

	public void setMark(boolean mark) {
		isMark = mark;
	}
}
