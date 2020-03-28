package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Representa a requisição feita ao coordenador do ambiente distrubuído.
 */
public class CoordinatorRequestTask extends Task {

	private Process requester;

	/**
	 * Contrutor padrão.
	 * 
	 * @param requester processo que requisitará ao coordenador
	 */
	public CoordinatorRequestTask(Process requester) {
		this.requester = requester;
	}

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo está ativo e ele próprio não é o coordenador.<br>
	 * Se o coordenador estiver ativo ele irá consumir a requisição.<br>
	 * Se ele não estiver ativo será iniciada uma eleição.
	 */
	@Override
	public void run() {
		synchronized (this) {
			if (requester.isActive() && !requester.isCoordinator()) {
				logOut("Processo " + requester.getId() + " fez uma requisição ao coordenador.");
				LogicRing.handleRequest(requester);
			}
		}
	}

	public Process getRequester() {
		return requester;
	}

}
