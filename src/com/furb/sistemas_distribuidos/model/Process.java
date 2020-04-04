package com.furb.sistemas_distribuidos.model;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.utils.ProcessIdCounter;

/**
 * Representa um processo genérico sendo executado no ambiente distribuído.
 */
public class Process {

	protected long id;

	/**
	 * Construtor padrão com id universal.
	 */
	public Process() {
		this.id = ProcessIdCounter.getAndIncrement();
	}

	/**
	 * Método utilitário que cria um processo com base em outro, copiando suas
	 * informações.
	 * 
	 * @param process processo a ser copiado
	 */
	protected Process(Process process) {
		this.id = process.id;
	}

	/**
	 * Envia uma requisição ao coordenador atual do ambiente distribuído.<br>
	 * Se não houver coordenador disponível irá iniciar uma eleição.
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
	 * Comparação feita pelo id.
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
