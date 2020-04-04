package com.furb.sistemas_distribuidos.task;

import com.furb.sistemas_distribuidos.LogicRing;

/**
 * Tarefa responsável por inativar o coordenador atual no ambiente distribuído.
 */
public class InactivateCoordinatorTask extends Task {

	@Override
	public void run() {
		LogicRing.deactivateCoordinator();
	}

}
