package com.studyhelper.test;

import java.util.*;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.enums.Region;
import com.studyhelper.enums.Subject;
import com.studyhelper.matching.job.match.algorithm.MatchTrie;

public class testmain {

	public static void main(String[] args) {
		MatchTrie matchTrie = new MatchTrie();
		
		for (int i = 0; i < 10; i++) {
			Matching matching = new Matching();
			matching.setRegion(Region.SEOUL_GANGNAM);
			matching.setSize(4);
			matching.setSubject(Subject.PROGRAMMING_C);

			matchTrie.pushMatching(matching);
		}
	}

}
