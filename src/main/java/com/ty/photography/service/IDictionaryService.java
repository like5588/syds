package com.ty.photography.service;

import java.util.List;

import com.ty.photography.model.Dictionary;

public interface IDictionaryService {
	/**
	 * 查询字典类型
	 * @param type
	 * @return
	 */
	public List<Dictionary> findByType(String type);
	/**
	 * 根据id查询字典信息
	 * @param id
	 * @return
	 */
	public Dictionary findById(String id);
	/**
	 * 更新字典值信息
	 * @param dictionary
	 * @return
	 */
	public Integer updateValue(Dictionary dictionary);
	/**
	 * 更新字典
	 * @param dictionary
	 * @return
	 */
	public Integer update(Dictionary dictionary);
	/**
	 * 新增
	 * @param dictionary
	 */
	public void save(Dictionary dictionary);
	
	public boolean deleteById(String id);
	
}
