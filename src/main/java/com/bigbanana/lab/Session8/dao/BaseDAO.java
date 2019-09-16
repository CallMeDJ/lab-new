package com.bigbanana.lab.Session8.dao;

import com.bigbanana.lab.Session8.dto.BaseDTO;

public abstract class BaseDAO {

	public abstract String getIdString(Long id);

	public void insertOrUpdate(BaseDTO baseDTO){
		String id = getIdString(baseDTO.getId());
		DB.db.put(id,baseDTO);
	}


	public BaseDTO select(Long  lId){
		String id = getIdString(lId);
		return (BaseDTO)DB.db.get(id);
	}


	public int delete(Long  lId){
		String id = getIdString(lId);

		if(!DB.db.containsKey(id)){
			return 0;
		}

		DB.db.remove(id);
		return 1;

	}

}
