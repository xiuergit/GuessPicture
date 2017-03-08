package com.briup.bean;

import java.util.Arrays;

public class Answer {
	// 标题
	private String title;
	//答案
	private String answer;
	//图片
	private int icon;
	//答案选项
	private String[] ViewAnswer;
	
	public String[] getViewAnswer() {
		return ViewAnswer;
	}
	public void setViewAnswer(String[] viewAnswer) {
		ViewAnswer = viewAnswer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	@Override
	public String toString() {
		return "Answer [title=" + title + ", answer=" + answer + ", icon=" + icon + ", ViewAnswer="
				+ Arrays.toString(ViewAnswer) + "]";
	}	

}
