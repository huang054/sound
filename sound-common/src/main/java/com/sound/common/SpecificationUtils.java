package com.sound.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpecificationUtils {
	
	public static final String GT = "gt";
	public static final String GE = "ge";
	public static final String LT = "lt";
	public static final String LE = "le";
	
	public static Predicate createPredicate(Object entity,Map<String,String> relationMap,Root<?> root,CriteriaBuilder cb) {
		Class clazz = entity.getClass();
		Field[]fields = clazz.getDeclaredFields();
		Predicate predicate = null;
		Predicate newPredicate = null;
		if(relationMap == null) {
			relationMap = new HashMap<String,String>();
		}
		for( Field f:fields ) {
			try {
				f.setAccessible(true);
				Object val = f.get(entity);
				if(null!= val) {
					String memberVarName = f.getName();
					String oprStr = relationMap.get(memberVarName) ;
					
					if (null == oprStr) {
						Path<Object> exp0 = root.get(f.getName());
						newPredicate = cb.and(cb.equal(exp0, val));
					}else {
						Class classType = f.getType();
						if(Number.class.isAssignableFrom(classType)) {
							Path<Number> exp0 = root.get(f.getName());
							if(GT.equals(oprStr)) {
								newPredicate = cb.and(cb.gt(exp0, ((Number) val)));
							}
							else if(GE.equals(oprStr)) {
								newPredicate = cb.and(cb.ge(exp0, ((Number) val)));
							}
							else if(LT.equals(oprStr)) {
								newPredicate = cb.and(cb.lt(exp0, ((Number) val)));
							}else if(LE.equals(oprStr)) {
								newPredicate = cb.and(cb.le(exp0, ((Number) val)));
							}
							
						}else if(Date.class.isAssignableFrom(classType)) {
							Path<Date> exp0 = root.get(f.getName());
							if(GT.equals(oprStr)) {
								newPredicate = cb.and(cb.greaterThan(exp0, ((Date) val)));
							}
							else if(GE.equals(oprStr)) {
								newPredicate = cb.and(cb.greaterThanOrEqualTo(exp0, ((Date) val)));
							}
							else if(LT.equals(oprStr)) {
								newPredicate = cb.and(cb.lessThan(exp0, ((Date) val)));
							}else if(LE.equals(oprStr)) {
								newPredicate = cb.and(cb.lessThanOrEqualTo(exp0, ((Date) val)));
							}
						}
					} 
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if(null == predicate) {
				predicate = newPredicate;
			}else if(newPredicate!=null){
				predicate = cb.and(predicate,newPredicate );
			}
			newPredicate = null;
		}
		return predicate;
	}
}