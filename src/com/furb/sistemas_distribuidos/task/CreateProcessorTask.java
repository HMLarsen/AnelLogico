package com.furb.sistemas_distribuidos.task;

import java.util.List;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.utils.Logger;

/**
 * Tarefa respons�vel por criar processos no ambiente distribu�do.
 */
public class CreateProcessorTask extends Task {

	/**
	 * Se for o primeiro processo ele se tornar� o coordenador.
	 */
	@Override
	public void run() {
		Process process = new Process();
		List<Process> processlist = LogicRing.getActiveProcesslist();
		processlist.add(process);
		Logger.log("Criado processo " + process.getId() + ".");

		// verificar se deve criar o coordenador
		if (processlist.size() == 1) {
			LogicRing.setCoordinator(process);
			Logger.log("Processo " + process.getId() + " � o novo coordenador.");
		}
	}

}
