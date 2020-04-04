package com.furb.sistemas_distribuidos.task;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.utils.Logger;
import com.furb.sistemas_distribuidos.utils.ProcessSelector;

/**
 * Tarefa respons�vel por escolher aleatoriamente um processo e fazer uma
 * requisi��o ao coordenador.
 */
public class CoordinatorRequestTask extends Task {

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo est� ativo e ele pr�prio n�o � o coordenador.
	 */
	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();
		if (process != null) {
			process.sendRequest();
			return;
		}
		Logger.log("Nenhum processo para fazer requisi��o ao coordenador.");
	}

}
