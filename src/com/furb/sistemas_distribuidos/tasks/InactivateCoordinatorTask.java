package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Tarefa responsável por inativar o coordenador atual no ambiente distribuído.
 */
public class InactivateCoordinatorTask extends Task {

	@Override
	public void run() {
		Process coordinator = ProcessSelector.getActiveCoordinator();
		if (coordinator != null) {
			coordinator.setCoordinator(false);
			logOut("Coordenador " + coordinator.getId() + " inativado.");
			return;
		}
		logOut("Nenhum coordenador ativo para ser inativado");
	}

}
