package com.furb.sistemas_distribuidos.util;

/**
 * Respons�vel por gerenciar todo o log do ambiente distribu�do.
 */
public class Logger {

	/**
	 * Mostra o log no console e quebra uma linha ap�s seu conte�do.
	 * 
	 * @param message mensagem a ser exibida
	 */
	public static void log(String message) {
		System.out.println(message + "\n");
	}

}
