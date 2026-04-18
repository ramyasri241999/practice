package com.epam.practice.customAnnotation;
/*
 * Annotation - metadata (data about data)
 */
public class CustomAnnoExample {

	@UppercaseCusAnn
	private String name; //since the annotation is annotated with @Target(ElementType.FIELD) it can be applied to fields only

	//@UppercaseCusAnn  cannot apply here since the annotation is annotated with @Target(ElementType.FIELD) it can be applied to fields only
	public void setdata(String name) {
		this.name = name;
	
	}
	 
	public String getdata() {
		return name;
	}
}



