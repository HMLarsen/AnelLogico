package com.furb.sistemas_distribuidos.task;

import com.furb.sistemas_distribuidos.LogicRing;

/**
 * Tarefa respons�vel por inativar o coordenador atual no ambiente distribu�do.
 */
public class InactivateCoordinatorTask extends Task {

	@Override
	public void run() {
		LogicRing.deactivateCoordinator();
	}

}
