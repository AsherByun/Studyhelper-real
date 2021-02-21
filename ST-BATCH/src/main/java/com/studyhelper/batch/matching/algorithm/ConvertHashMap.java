package com.studyhelper.batch.matching.algorithm;

import java.util.HashMap;
import java.util.Iterator;

import com.domain.enums.Region;
import com.domain.enums.Subject;


public class ConvertHashMap {
	public HashMap<String,Integer> converter;
	
	public ConvertHashMap() {
		converter = new HashMap<String, Integer>();
		for(int i=0;i<Subject.values().length;i++) {
			converter.put(Subject.values()[i].toString(), i);
		}
		for(int i=0;i<Region.values().length;i++) {
			converter.put(Region.values()[i].toString(), i);
		}
	}
	public int converting(String str) {
		return converter.get(str);
	}
	
}
