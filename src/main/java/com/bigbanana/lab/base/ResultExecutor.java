package com.bigbanana.lab.base;

public abstract class ResultExecutor<T> {
	public abstract T run();


	public ResultDTO<T> execute(){

		ResultDTO<T> resultDTO = ResultDTO.of(true);
		try {

			T data = run();
			resultDTO.setData(data);

		}catch (Throwable e){
			resultDTO.setSuccess(false);
		}


		return resultDTO;
	}




}
