package com.epam.practice.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 1.Retention: how long the annotation should be retained. RetentionPolicy can be SOURCE, CLASS, or RUNTIME.
 * 2.Target: where the annotation can be applied. ElementType can be TYPE, FIELD, METHOD, PARAMETER, etc.
 * 
 * RetentionPolicy :
 * 1. source: the annotation is only available in the source code and is discarded by the compiler.
 * 2. class: the annotation is available in the class file but is not available at runtime.
 * 3. runtime: the annotation is available at runtime and can be accessed through reflection.
 * 
 * ElementType :
 * 1. type: the annotation can be applied to classes, interfaces, enums, and annotations.
 * 2. field: the annotation can be applied to fields (variables).
 * 3. method: the annotation can be applied to methods.
 * 4. parameter: the annotation can be applied to method parameters.
 * 5. constructor: the annotation can be applied to constructors.
 * 6. local_variable: the annotation can be applied to local variables.
 * 7. annotation_type: the annotation can be applied to other annotations.
 * 8. package: the annotation can be applied to packages.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UppercaseCusAnn {
 /*
  * without processing the annotation, it will not do anything. we need to write a processor to process the annotation and convert the value of the field to uppercase.
  */
	
}
