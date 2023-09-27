package com.loop.demo.util;


import com.loop.demo.exception.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 自定义注解，校验工具
 */
public class ValidationUtils {
	
	private static Validator validator;
	
	static{
			validator =  Validation.byDefaultProvider()
					     .configure()
					     .addProperty( "hibernate.validator.fail_fast", "true" )
//					     .messageInterpolator(new ValidateInternationalization())
					     .buildValidatorFactory()
					     .getValidator();
	}

	/**
	 * 校验自定义注解参数
	 * @param t
	 * @return
	 */
	public static<T> void validate(T t) {
		Set<ConstraintViolation<T>> set=validator.validate(t);
		if(set.size()>0){
			for (ConstraintViolation<T> cv : set) {
				throw new BusinessException(cv.getMessage());
			}
		}

	}

	/**
	 * 校验自定义注解参数(分组校验)
	 * @param t 校验对象
	 * @param groups 分组
	 */
	public static<T> void validate(T t,Class<?>... groups) {
		Set<ConstraintViolation<T>> set=validator.validate(t, groups);
		if(set.size()>0){
			for (ConstraintViolation<T> cv : set) {
				throw new BusinessException(cv.getMessage());
			}
		}

	}


}