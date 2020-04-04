package com.furb.sistemas_distribuidos.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Classe utilitária para selecionar processos no ambiente distribuído.
 */
public class ProcessSelector {

	/**
	 * @return um processo ativo aleatório (não sendo o coordenador)
	 */
	public static Process getRandomActiveNotCoordinator() {
		Random random = new Random();
		int size = LogicRing.getProcesslist().size();

		// REVER
		return LogicRing.getProcesslist().stream().filter(item -> item.isActive() && !item.isCoordinator())
				.skip(random.nextInt(size)).findFirst().orElse(null);
	}

	/**
	 * @return coordenador atual e ativo do ambiente distribuído
	 */
	public static Process getActiveCoordinator() {
		return LogicRing.getProcesslist().stream().filter(process -> process.isActive() && process.isCoordinator())
				.findFirst().orElse(null);
	}

	/**
	 * @return o processo eleito como cordenador (processo com maior id)
	 */
	public static Process makeElection() {
		List<Process> processes = LogicRing.getProcessElectionlist();

		Collections.sort(processes, new Comparator<Process>() {
			@Override
			public int compare(Process o1, Process o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});

		return processes.stream().findFirst().get();
	}

}
