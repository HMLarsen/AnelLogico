package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Inativa um processo genérico de forma aleatória no ambiente distribuído.
 */
public class InactivateProcessTask extends Task {

	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();
		if (process != null) {
			process.setActive(false);
			logOut("Processo " + process.getId() + " inativado.");
			return;
		}
		logOut("Nenhum processo ativo para ser desativado.");
	}

}
