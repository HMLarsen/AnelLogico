package com.furb.sistemas_distribuidos.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Representa um processo genérico sendo executado no ambiente distribuído.
 */
public class Process {

	private static AtomicLong counter = new AtomicLong(1);

	private long id;
	private boolean active;
	private boolean coordinator;

	public Process() {
		this.id = counter.getAndIncrement();
		this.active = true;
		this.coordinator = false;
	}

	public long getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isCoordinator() {
		return coordinator;
	}

	public void setCoordinator(boolean coordinator) {
		this.coordinator = coordinator;
	}

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
		return "Processo: " + id + "\nAtivo: " + active + "\nCoordenador: " + coordinator;
	}

}
