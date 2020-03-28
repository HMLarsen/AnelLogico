package com.furb.sistemas_distribuidos.util;

/**
 * Responsável por gerenciar todo o log do ambiente distribuído.
 */
public class Logger {

	/**
	 * Mostra o log no console e quebra uma linha após seu conteúdo.
	 * 
	 * @param message mensagem a ser exibida
	 */
	public static void log(String message) {
		System.out.println(message + "\n");
	}

}
