package com.ty.photography.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ty.photography.model.Dictionary;

@Repository
public interface DictionaryMapper {
	
	public List<Dictionary> findByType(String type);
	
	public Dictionary findById(String id);
	
	public Integer updateValue(Dictionary dictionary);
	
	public Integer update(Dictionary dictionary);
	
	public void save(Dictionary dictionary);

	public Integer deleteById(String id);
	
}
