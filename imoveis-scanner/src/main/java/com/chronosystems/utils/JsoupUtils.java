/**
 * 
 */
package com.chronosystems.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe utilit�ria do Jsoup
 * 
 * @author Andr� Valadas
 */
public final class JsoupUtils {

	public static Integer stringToInteger(final String stringValue) {
		if (stringValue != null) {
			if (isNumber(stringValue)) {
				return Integer.valueOf(stringValue);
			} else {
				//verifica se tem espa�o na string pega primeiro numero ex.: "5 ou mais"
				final int ws = stringValue.indexOf(" ");
				if (ws > 0) {
					return Integer.valueOf(stringValue.substring(0, ws));
				}
			}
		}
		return Integer.valueOf(0);
	}

	/**
     * Validates if input String is a number
     */
    public static boolean isNumber(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

	/**
	 * Converte String para Valor decimal
	 * 
	 * @param stringValue
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(final String stringValue) {
		if (stringValue != null) {
			//your way short version
	        final NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
	        try {
	            final BigDecimal decimal = new BigDecimal(nf.parse(stringValue).toString());
	            return decimal;
	        } catch (ParseException e) { }
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Procura o valor da propriedade no javaScript do documento da p�gina
	 * 
	 * @param doc
	 * @param property
	 * @return
	 */
	public static String getScriptPropertyValue(final Document doc, final String property) {
		final Elements scriptElements = doc.getElementsByTag("script");

		for (final Element element : scriptElements) {
			for (final DataNode node : element.dataNodes()) {
				final String wholeData = node.getWholeData();
				final String propertyValue = getScriptPropertyValue(wholeData, property);
				if (propertyValue != null) {
					return propertyValue;
				}
			}
		}		
		return null;
	}

	/**
	 * Procura o valor da propriedade no javaScript da p�gina
	 * 
	 * @param scriptText
	 * @param property
	 * @return
	 */
	public static String getScriptPropertyValue(final String scriptText, final String property) {
		final int propertyIndex = scriptText.indexOf(property);
		if (propertyIndex > -1) {
			final int initProp = scriptText.indexOf("\"", propertyIndex);
			final int endProp = scriptText.indexOf("\"", initProp+1);
			final String propertyValue = scriptText.substring(initProp+1, endProp).trim();
			
			//System.out.println(propertyValue);
			return propertyValue;
		}
		return null;
	}
}