package com.anderson.engdb.services.utils;

public class PadronizarNome {

	public static String removeWhiteSpacesTogether(String str) {
		return str.trim().replaceAll("\\s+", " ");
	}
}
