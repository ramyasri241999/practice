package com.epam.practice.customAnnotation;

public class UppercaseProcessor {
	
	/*
	 * lets see how we can process the Uppercase annotation at runtime using reflection.
	 *  We will create a method that takes an object as a parameter and checks if any of its fields are annotated with @Uppercase. 
	 *  If it finds such a field, it will convert its value to uppercase.
	 */
	public static void processUpperCase(Object obj) {
		Class<?> clazz = obj.getClass();
		for(var field : clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(UppercaseCusAnn.class)) {
				field.setAccessible(true);
				try {
					Object value = field.get(obj);
					if(value instanceof String) {
						String upperValue = ((String) value).toUpperCase();
						field.set(obj, upperValue);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
		public static void main(String[] args) {
			
			CustomAnnoExample example = new CustomAnnoExample();
			example.setdata("ramya");
			processUpperCase(example);
			System.out.println(example.getdata()); // Output: RAMYA
		}
	}


