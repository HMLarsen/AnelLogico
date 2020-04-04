package com.furb.sistemas_distribuidos.task;

import java.util.List;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.utils.Logger;

/**
 * Tarefa responsável por criar processos no ambiente distribuído.
 */
public class CreateProcessorTask extends Task {

	/**
	 * Se for o primeiro processo ele se tornará o coordenador.
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
			Logger.log("Processo " + process.getId() + " é o novo coordenador.");
		}
	}

}
