package com.furb.sistemas_distribuidos.model;

import com.furb.sistemas_distribuidos.utils.Logger;

/**
 * Representa o coordenador do ambiente distribuído.
 */
public class Coordinator extends Process {

	public Coordinator() {
		super();
	}

	/**
	 * Método utilitário que cria um coordenador com base em um processo, copiando
	 * suas informações.
	 * 
	 * @param process processo a ser copiado
	 */
	public Coordinator(Process process) {
		super(process);
	}

	/**
	 * Trata a requisição de um processo.
	 * 
	 * @param process processo que requisitou recursos
	 */
	public void handleRequest(Process process) {
		Logger.log("Coordenador " + getId() + " recebeu a requisição do processo " + process.getId() + ".");
	}

}
