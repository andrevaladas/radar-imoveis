package com.chronosystems.entity.enumeration.util;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.chronosystems.entity.enumeration.BaseEnum;
import com.chronosystems.entity.enumeration.Estado;


public abstract class EnumUtils {
	
	public static void main(String[] args) {
		EnumUtils.findByValue(Estado.class, "RS");
	}

	public static <T extends Enum<T>> Map<String, Object> toMap(Class<T> enumType) {
		SortedMap<String, Object> data = new TreeMap<String, Object>();
		for (T c : enumType.getEnumConstants()) {
			if (c instanceof BaseEnum) {
				data.put(c.name(), ((BaseEnum)c).getDescription());
			}
		}
		return data;
	}

	public static <T extends Enum<T>> T findByValue(Class<T> enumType, String name) {
        for (T c : enumType.getEnumConstants()) {
             if (c.name().equals(name)) {
            	 return c;
             }
        }
        return null;
	}
}
