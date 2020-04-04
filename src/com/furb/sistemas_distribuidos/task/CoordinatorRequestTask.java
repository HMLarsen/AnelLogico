package com.furb.sistemas_distribuidos.task;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.utils.Logger;
import com.furb.sistemas_distribuidos.utils.ProcessSelector;

/**
 * Tarefa responsável por escolher aleatoriamente um processo e fazer uma
 * requisição ao coordenador.
 */
public class CoordinatorRequestTask extends Task {

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo está ativo e ele próprio não é o coordenador.
	 */
	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();
		if (process != null) {
			process.sendRequest();
			return;
		}
		Logger.log("Nenhum processo para fazer requisição ao coordenador.");
	}

}
