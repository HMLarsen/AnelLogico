package com.furb.sistemas_distribuidos.tasks;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Representa a requisi��o feita ao coordenador do ambiente distrubu�do.
 */
public class CoordinatorRequestTask extends Task {

	private Process requester;

	/**
	 * Contrutor padr�o.
	 * 
	 * @param requester processo que requisitar� ao coordenador
	 */
	public CoordinatorRequestTask(Process requester) {
		this.requester = requester;
	}

	/**
	 * Inicia a request ao coordenador.<br>
	 * Somente se o processo est� ativo e ele pr�prio n�o � o coordenador.<br>
	 * Se o coordenador estiver ativo ele ir� consumir a requisi��o.<br>
	 * Se ele n�o estiver ativo ser� iniciada uma elei��o.
	 */
	@Override
	public void run() {
		synchronized (this) {
			if (requester.isActive() && !requester.isCoordinator()) {
				logOut("Processo " + requester.getId() + " fez uma requisi��o ao coordenador.");
				LogicRing.handleRequest(requester);
			}
		}
	}

	public Process getRequester() {
		return requester;
	}

}
