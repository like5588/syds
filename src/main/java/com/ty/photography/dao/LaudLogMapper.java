package com.ty.photography.dao;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.LaudLog;


@Repository
public interface LaudLogMapper {
	
	public void insert(LaudLog laudLog);

}
