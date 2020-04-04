package com.furb.sistemas_distribuidos.utils;

public class ProcessIdCounter {

	private static volatile long counter = 1;

	/**
	 * Retorna e incrementa o id universal dos processos.
	 * 
	 * @return novo id para o processo
	 */
	public static long getAndIncrement() {
		return counter++;
	}

}
