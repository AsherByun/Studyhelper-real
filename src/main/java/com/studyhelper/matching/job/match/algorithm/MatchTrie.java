package com.studyhelper.matching.job.match.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.studyhelper.entity.Matching;
import com.studyhelper.enums.Gender;
import com.studyhelper.enums.Region;
import com.studyhelper.enums.Subject;

public class MatchTrie {
	public final ConvertHashMap convert;
	public final int REGION_LENGTH = Region.values().length;
	public final int SUBJECT_LENGTH = Subject.values().length;
	public final int MAX_SIZE = 6;
	
	public List<Matching>[][][] matchs;
	
	
	public MatchTrie() {
		convert = new ConvertHashMap();
		matchs = new ArrayList[REGION_LENGTH][SUBJECT_LENGTH][MAX_SIZE];
		setMatchs();
	}
	
	public void setMatchs() {
		for(int i=0;i<REGION_LENGTH;i++) {
			for(int j=0;j<SUBJECT_LENGTH;j++) {
				for(int k=0;k<MAX_SIZE;k++) {
					matchs[i][j][k] = new ArrayList<Matching>();
				}
			}
		}
	}
	
	public void pushMatching(Matching match) {
		int subjectNum = convert.converting(match.getSubject().toString());
		int regionNum = convert.converting(match.getRegion().toString());
		int sizeNum = match.getSize();
		
		matchs[regionNum][subjectNum][sizeNum].add(match);
		if (matchs[regionNum][subjectNum][sizeNum].size() == sizeNum) {
			for(Matching matching : matchs[regionNum][subjectNum][sizeNum]) {
				System.out.println(">>>>>>>>>>"+matching.getRegion()+matching.getSubject());
			}
			matchs[regionNum][subjectNum][sizeNum] = new ArrayList<Matching>();  //삭제시켜야된다 --디비도접근
		}
	}
}
