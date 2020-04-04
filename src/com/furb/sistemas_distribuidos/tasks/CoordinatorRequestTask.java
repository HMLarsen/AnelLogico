package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Representa a requisi��o feita ao coordenador do ambiente distrubu�do.
 */
public class CoordinatorRequestTask extends Task {

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo est� ativo e ele pr�prio n�o � o coordenador.<br>
	 * Se o coordenador estiver ativo ele ir� consumir a requisi��o.<br>
	 * Se ele n�o estiver ativo ser� iniciada uma elei��o.
	 */
	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();

		if (process != null) {
			logOut("Processo " + process.getId() + " fez uma requisi��o ao coordenador.");
			LogicRing.handleRequest(process);
		}
	}

}
