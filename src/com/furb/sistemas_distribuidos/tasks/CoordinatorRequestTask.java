package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Representa a requisição feita ao coordenador do ambiente distrubuído.
 */
public class CoordinatorRequestTask extends Task {

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo está ativo e ele próprio não é o coordenador.<br>
	 * Se o coordenador estiver ativo ele irá consumir a requisição.<br>
	 * Se ele não estiver ativo será iniciada uma eleição.
	 */
	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();

		if (process != null) {
			logOut("Processo " + process.getId() + " fez uma requisição ao coordenador.");
			LogicRing.handleRequest(process);
		}
	}

}
