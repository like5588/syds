package com.ty.photography.common;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class ComparableList implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
		HashMap<String,Object> m1 = (HashMap<String,Object>)o1;
		HashMap<String,Object> m2 = (HashMap<String,Object>)o2;
		int p1 = ((BigDecimal)m1.get("pnum")).intValue();
		int p2 = ((BigDecimal)m2.get("pnum")).intValue();
		return p1<p2?1:(p1>p2?-1:0);
	}
	
	public static void main	(String args[]){
		List list=new ArrayList();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unum", 5);
		map.put("pnum", 17);
		map.put("name", "北京");
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("unum", 20);
		map.put("pnum", 10);
		map.put("name", "天津");
		list.add(map);
		map = new HashMap<String,Object>();
		map.put("unum", 9);
		map.put("pnum", 20);
		map.put("name", "上海");
		list.add(map);
		   
		ComparableList comparator=new ComparableList();
		Collections.sort(list, comparator);
		for(int i=0;i<list.size();i++){
			Map resultMap = (HashMap<String,Object>)list.get(i);
			Iterator it = resultMap.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				System.out.print(key+":"+resultMap.get(key)+" ");
			}
			System.out.println("");
		}
	}


}
