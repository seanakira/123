package com.cts.localtour.pojo;

import java.util.HashMap;

public class TreeChildren {
	private HashMap<Integer,TreeElement> children;

	public HashMap<Integer, TreeElement> getChildren() {
		if(children==null){
			children = new HashMap<Integer, TreeElement>();
		}
		return children;
	}

	public void setChildren(HashMap<Integer, TreeElement> children) {
		this.children = children;
	}
}
