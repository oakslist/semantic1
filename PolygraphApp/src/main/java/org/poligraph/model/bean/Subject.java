package org.poligraph.model.bean;

import java.io.Serializable;

public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String subject;
	private String subjectShort;
	
	public Subject() {
		
	}
	
	public Subject (String subject) {
		setSubject(subject);
	}
	
	private String getShortValue(String str) {
		String[] array = str.split("/");
		array = array[array.length - 1].split("#");
		return array[array.length - 1].replace("#", ":")
				.substring(0, array[array.length - 1].length());
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
		setSubjectShort(this.subject);
	}
	
	public String getSubjectShort() {
		return subjectShort;
	}

	public void setSubjectShort(String subjectShort) {
		this.subjectShort = getShortValue(subjectShort);;
	}

	@Override
	public String toString() {
		return "Subject [subject=" + subject + ", subjectShort=" + subjectShort
				+ "]";
	}
	
	
}
