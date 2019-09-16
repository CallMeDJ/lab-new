package com.bigbanana.lab.base;


import lombok.Data;

@Data
public class ResultDTO<T> {
	private boolean success;
	private T data;
	private String errorCode;



	public static <T>  ResultDTO of(T data){
		ResultDTO<T> resultDTO = ResultDTO.of(true);
		resultDTO.setData(data);
		return resultDTO;
	}


	public static <T>  ResultDTO<T> of(boolean isSuccess){
		ResultDTO<T> resultDTO = new ResultDTO<>();
		resultDTO.setSuccess(isSuccess);
		return resultDTO;
	}


}
