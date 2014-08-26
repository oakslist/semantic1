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
		setSubject(subject);
		setPredicate(predicate);
		setObject(object);
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

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
		setPredicateShort(this.predicate);
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
		setObjectShort(this.object);
	}
	
	/*short value*/
	
	public String getSubjectShort() {
		return subjectShort;
	}

	public void setSubjectShort(String subjectShort) {
		this.subjectShort = getShortValue(subjectShort);;
	}

	public String getPredicateShort() {
		return predicateShort;
	}

	public void setPredicateShort(String predicateShort) {
		this.predicateShort = getShortValue(predicateShort);;
	}

	public String getObjectShort() {
		return objectShort;
	}

	public void setObjectShort(String objectShort) {
		this.objectShort = getShortValue(objectShort);
	}
	
	@Override
	public String toString() {
		return "Triple [subject=" + subject + ", predicate=" + predicate
				+ ", object=" + object + "]";
	}	
}
