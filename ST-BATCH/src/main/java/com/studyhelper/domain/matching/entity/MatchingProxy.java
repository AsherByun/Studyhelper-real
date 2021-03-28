package com.studyhelper.domain.matching.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MatchingProxy {
	private Matching matching;
	private Queue<String> queue;

	public MatchingProxy(Matching matching) {
		this.matching = matching;
	}

}
