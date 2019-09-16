package com.bigbanana.lab.Session7;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class JVM {


	/**
	 * 整个内存区大小
	 */
	private Integer Xmx = 50;

	/**
	 * young 区的大小
	 */

	private Integer Xmn = 10;


	/**
	 *  持久代大小
	 */
	private Integer XXPermSize = 10;


	public JVM(Integer Xmx , Integer Xmn , Integer XXPermSize){
		this.Xmn = Xmn;
		this.Xmx = Xmx;
		this.XXPermSize = XXPermSize;

		YOUNG_GEN_SPACE = new ArrayList<>(Xmn);
		OLD_GEN_SPACE = new ArrayList<>(Xmx - Xmn);
		PERM_GEN_SPACE = new ArrayList<>(XXPermSize);
	}


	/**
	 * 这里需要自己划分 Eden 和 Survivor 的大小，千万不要触发扩容
	 */
	private List<JMemory> YOUNG_GEN_SPACE ;


	/**
	 * 老年代，千万不要触发扩容
	 */
	private  List<JMemory> OLD_GEN_SPACE;

	/**
	 * 持久代(JVM 内存)，JDK 1.8 后为 MetaSpace(物理内存)，千万不要触发扩容
	 */
	private  List<JMemory> PERM_GEN_SPACE;


	private static final ConcurrentMap<String,Integer> GC_ROOT = Maps.<String,Integer> newConcurrentMap();

	public  void addObject(String name , Object value){
		gc();



	}

	public void removeObject(String name){
		gc();

	}


	public  void addPermObject(String name , Object value){
		gc();



	}

	public  void removePermObject(String name){
		gc();



	}





	/**
	 * 这里自己实现 gc 算法，比如什么时候应该进行代的晋升，什么时候进行young gc，什么时候进行full gc，什么时候应该crash。
	 * 核心逻辑：
	 * 新对象优先放 Eden 区，然后经过一定轮数晋升到 Survivor 区域。Survivor 区域分为两个区域循环利用。
	 * 对象 gc 经过一定轮数后，从 Survivor 区晋升到 Old 区。
	 * 如果 old 区放不下了，进行full gc。
	 * 如果 永久代放不下了，进行full gc。
	 * PS : 本作业不要求多线程回收，每次回收就 stop the world 好了，暂时不要太纠结这块的性能
	 *
	 */
	private  void gc(){



	}


	private  void checkMemory(){
		if(YOUNG_GEN_SPACE.size() > Xmn){
			throw new OutOfMemoryError("OutOfMemory Error,YoungGen full");
		}

		if(OLD_GEN_SPACE.size() > Xmx - Xmn){
			throw new OutOfMemoryError("OutOfMemory Error,OldGen full ");
		}

		if(PERM_GEN_SPACE.size() > XXPermSize){
			throw new OutOfMemoryError("OutOfMemory Error,PermGen full ");
		}


	}


}
