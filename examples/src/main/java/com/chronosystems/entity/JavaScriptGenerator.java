package com.chronosystems.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JavaScriptGenerator {

	public static void main(String[] args) {
		final Class<?> mainClass = Imovel.class;

		final String filePath = mainClass.getCanonicalName().replaceAll("\\.", "/").concat(".js");
		info(filePath);

		final StringBuilder script = new StringBuilder();
		script.append("//Classe ").append(mainClass.getSimpleName()).append("\n");
		script.append("function ").append(mainClass.getSimpleName()).append("(){\n\n");

		script.append("	//@interface").append("\n");
		final List<String> paramNames = new ArrayList<String>();
		final Field[] declaredFields = mainClass.getDeclaredFields();
		for (Field field : declaredFields) {
			paramNames.add(field.getName());
			script.append("	var ").append(field.getName());
			if (field.getType().isArray()) {
				script.append(" = []");
			}
			script.append(";").append("\n");
		}
		script.append("	//@end").append("\n\n");

		//TODO construtores

		script.append("	//@implementation").append("\n");
		final Method[] methods = mainClass.getDeclaredMethods();
		for (final Method method : methods) {
			final String name = method.getName();
			script.append("	this.").append(name).append("(");

			boolean isEnum = false;
			final Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				final Class<?> paramClass = parameterTypes[i];

				if (i > 0) {
					script.append(", ");
				}
				script.append("_value");
				if (paramClass.isEnum()) {
					script.append(", _description");
					isEnum = true;
				}
			}
			script.append("){").append("\n");

			final Class<?> returnType = method.getReturnType();
			String paramName = name.replaceAll("get", "").replaceAll("set", "");
			paramName = Character.toLowerCase(paramName.charAt(0)) + paramName.substring(1);
			if (returnType.equals(Void.TYPE)) {
				script.append("		this.").append(paramName).append(" = ");
				if (isEnum) {
					script.append("new Enum(_value, _description);\n");
				} else {
					script.append("_value;\n");
				}
			} else {
				if (paramNames.contains(paramName)) {
					script.append("		return this.").append(paramName).append(";\n");
				} else {
					script.append("		return null;\n");
				}
			}
			script.append("	};\n");
		}
		script.append("	//@end").append("\n");
		script.append("}");
		info(script.toString());

		writeScript(filePath, script);
	}
	
	private static void info(String info) {
		System.out.println("| info: "+info);
	}

	private static void writeScript(String filePath, StringBuilder content) {
		
		final File directory = new File("/"+filePath.substring(0, filePath.lastIndexOf("/")));
		final File file = new File("/"+filePath);
		try {
			if (!directory.exists()) {
				directory.mkdirs();
				directory.setExecutable(true);
				directory.setReadable(true);
				directory.setWritable(true);
			}

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			// get the content in bytes
			byte[] contentInBytes = content.toString().getBytes();

			final FileOutputStream fop = new FileOutputStream(file);
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			System.out.println("Script Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
