package com.furb.sistemas_distribuidos.model;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.utils.ProcessIdCounter;

/**
 * Representa um processo gen�rico sendo executado no ambiente distribu�do.
 */
public class Process {

	protected long id;

	/**
	 * Construtor padr�o com id universal.
	 */
	public Process() {
		this.id = ProcessIdCounter.getAndIncrement();
	}

	/**
	 * M�todo utilit�rio que cria um processo com base em outro, copiando suas
	 * informa��es.
	 * 
	 * @param process processo a ser copiado
	 */
	protected Process(Process process) {
		this.id = process.id;
	}

	/**
	 * Envia uma requisi��o ao coordenador atual do ambiente distribu�do.<br>
	 * Se n�o houver coordenador dispon�vel ir� iniciar uma elei��o.
	 */
	public void sendRequest() {
		Coordinator coordinator = LogicRing.getCoordinator();
		if (coordinator != null) {
			coordinator.handleRequest(this);
		} else {
			LogicRing.makeElection(getId());
		}
	}

	public long getId() {
		return id;
	}

	/**
	 * Compara��o feita pelo id.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Process))
			return false;
		return id == ((Process) obj).getId();
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

}
