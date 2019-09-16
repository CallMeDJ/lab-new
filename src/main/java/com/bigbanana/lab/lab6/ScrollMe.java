package com.bigbanana.lab.lab6;

import com.bigbanana.lab.utils.Printer;

public abstract class ScrollMe {


	public abstract void scroll(int num);


	public void testing(ScrollMe scrollMe){
		for(int i = 1 ; i < 10 ;i++){
			scrollMe.scroll(i);
		}
	}
}
