package org.poligraph.model.bean;

import java.io.Serializable;

public class Triple implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String subject;
	private String predicate;
	private String object;
	
	private String subjectShort;
	private String predicateShort;
	private String objectShort;
	
	public Triple() {
		
	}
	
	public Triple (String subject, String predicate, String object) {
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
		setSubjectShort(this.subject);
		setPredicateShort(this.predicate);
		setObjectShort(this.object);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}
	
	/*short value*/
	
	public String getSubjectShort() {
		return subjectShort;
	}

	public void setSubjectShort(String subjectShort) {
		String[] array = subjectShort.split("/");
		array = array[array.length - 1].split("#");
		String str = array[array.length - 1].replace("#", ":").substring(0, array[array.length - 1].length());
		this.subjectShort = str;
	}

	public String getPredicateShort() {
		return predicateShort;
	}

	public void setPredicateShort(String predicateShort) {
		String[] array = predicateShort.split("/");
		String str = array[array.length - 1].replace("#", ":").substring(0, array[array.length - 1].length());
		this.predicateShort = str;
	}

	public String getObjectShort() {
		return objectShort;
	}

	public void setObjectShort(String objectShort) {
		String[] array = objectShort.split("/");
		array = array[array.length - 1].split("#");
		String str = array[array.length - 1].replace("#", ":").substring(0, array[array.length - 1].length());
		this.objectShort = str;
	}

	@Override
	public String toString() {
		return "Triple [subject=" + subject + ", predicate=" + predicate
				+ ", object=" + object + "]";
	}	
}
