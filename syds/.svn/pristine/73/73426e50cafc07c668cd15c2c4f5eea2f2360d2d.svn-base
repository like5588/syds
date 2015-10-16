package com.ty.photography.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.dao.DictionaryMapper;
import com.ty.photography.model.Dictionary;
import com.ty.photography.service.IDictionaryService;

@Service
public class DictionaryServiceImpl implements IDictionaryService{
	
	@Autowired
	private DictionaryMapper dictionaryMapper;

	@Override
	public List<Dictionary> findByType(String type) {
		return dictionaryMapper.findByType(type);
	}

	@Override
	public Dictionary findById(String id) {
		return dictionaryMapper.findById(id);
	}

	@Override
	public Integer updateValue(Dictionary dictionary) {
		return dictionaryMapper.updateValue(dictionary);
	}
	
	public Integer update(Dictionary dictionary){
		return dictionaryMapper.update(dictionary);
	}

	@Override
	public void save(Dictionary dictionary) {
		dictionaryMapper.save(dictionary);
	}

	@Override
	public boolean deleteById(String id) {
		Integer num = dictionaryMapper.deleteById(id);
		if(num == null){
			return false;
		}
		return true;
	}

}
