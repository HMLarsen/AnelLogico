package com.furb.sistemas_distribuidos.model;

import com.furb.sistemas_distribuidos.utils.Logger;

/**
 * Representa o coordenador do ambiente distribu�do.
 */
public class Coordinator extends Process {

	public Coordinator() {
		super();
	}

	/**
	 * M�todo utilit�rio que cria um coordenador com base em um processo, copiando
	 * suas informa��es.
	 * 
	 * @param process processo a ser copiado
	 */
	public Coordinator(Process process) {
		super(process);
	}

	/**
	 * Trata a requisi��o de um processo.
	 * 
	 * @param process processo que requisitou recursos
	 */
	public void handleRequest(Process process) {
		Logger.log("Coordenador " + getId() + " recebeu a requisi��o do processo " + process.getId() + ".");
	}

}
