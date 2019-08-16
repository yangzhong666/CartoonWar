package com.neudeu.homework2;

public class SingleChoice  extends Question {
    //选项
	String[] optiosn;
	//单选标准答案
	int answer;

	//	构造器
	public SingleChoice(String text,String[] optiosn, int answer) {
		this.text = text;
		this.optiosn = optiosn;
		this.answer = answer;
	}
	@Override
	public boolean check(int[] answers) {
		return this.answer == answers[0];
	}
	
	
	
}
