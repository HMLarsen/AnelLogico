package com.furb.sistemas_distribuidos.task;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.utils.Logger;
import com.furb.sistemas_distribuidos.utils.ProcessSelector;

/**
 * Tarefa respons�vel por inativar um processo de forma aleat�ria no ambiente
 * distribu�do.
 */
public class InactivateProcessTask extends Task {

	@Override
	public void run() {
		Process process = ProcessSelector.getRandomActiveNotCoordinator();
		if (process != null) {
			LogicRing.deactivateProcess(process);
			return;
		}
		Logger.log("Nenhum processo para ser inativado.");
	}

}
