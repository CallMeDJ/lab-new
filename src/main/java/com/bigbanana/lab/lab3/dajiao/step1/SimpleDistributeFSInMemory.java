package com.bigbanana.lab.lab3.dajiao.step1;

import com.google.common.primitives.Bytes;

import java.util.*;

public class SimpleDistributeFSInMemory {

	public static Map<String,Map<String,List<Byte>>> servers= new HashMap<>();
	public static Integer serverSize = 3;

	static {
		/**
		 * 初始化三台虚拟的机器
		 */
		for(int i = 0 ; i < serverSize ;i++){
			Map<String,List<Byte>> server = new HashMap<>();
			servers.put(i+"",server);
		}

	}

	public static void main(String[] args){
		/**
		 * 命令行初始化
		 */
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()){
			String command  = scanner.nextLine();
			String[] commandArray = command.split(" ");

			String targetCommand = commandArray[0];
			String fileName = commandArray[1];


			/**
			 * 根据文件名找到文件所在的服务器，并获得对应文件池的索引
			 */
			int serverIndex = getServerIndex(fileName);
			Map<String,List<Byte>> serverFile = servers.get(serverIndex+"");

			if("get".equals(targetCommand)){
				println("getting file from server "+serverIndex +"....");

				/**
				 * 如果是get 从文件桶中找到对应名称的文件，并把它转成String 输出出来。
				 */
				println(new String(Bytes.toArray(serverFile.getOrDefault(fileName,new ArrayList<>()))));

			}else if("put".equals(targetCommand)){
				/**
				 * 如果是put 把文件(这里是 String)转换成 byte 数组，并把它保存到文件桶中
				 */
				println("putting file to server "+serverIndex +"....");
				String file = commandArray[2];
				List<Byte> fileBytes = Bytes.asList(file.getBytes());

				serverFile.put(fileName,fileBytes);

				println("success");
			}
		}

	}

	/**
	 * 打印的工具类
	 */
	public static void println(Object o){
		System.out.println(o);
	}

	/**
	 * 根据hash获取文件服务的编号
	 */
	public static Integer getServerIndex(String fileName){
		return fileName.hashCode() % serverSize;
	}





}
