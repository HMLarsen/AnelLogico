package com.furb.sistemas_distribuidos.tasks;

import java.util.List;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Cria processos genéricos no ambiente distribuído.
 */
public class CreateProcessorTask extends Task {

	/**
	 * Se for o primeiro processo ele se tornará o coordenador.<br>
	 * Ao fim da criação do processo, comecará as suas requisições ao coordenador.
	 */
	@Override
	public void run() {
		Process process = new Process();
		List<Process> processlist = LogicRing.getProcesslist();
		String name = "processo";
		if (processlist.isEmpty()) {
			process.setCoordinator(true);
			name = "coordenador";
		}
		processlist.add(process);
		logOut("Criado " + name + " " + process.getId() + ".");
		LogicRing.initProcessRequestLapse(process);
	}

}
