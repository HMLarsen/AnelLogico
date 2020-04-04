package com.furb.sistemas_distribuidos.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Coordinator;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Classe utilitária para selecionar processos no ambiente distribuído.
 */
public class ProcessSelector {

	/**
	 * Seleciona um processo aleatório na lista de processos ativos (não sendo o
	 * coordenador).
	 * 
	 * @return um processo ativo aleatório (não sendo o coordenador)
	 */
	public static Process getRandomActiveNotCoordinator() {
		// copiar a lista para não alterar a original
		List<Process> activeProcesslist = new ArrayList<>(LogicRing.getActiveProcesslist());
		int size = activeProcesslist.size();

		// se a lista tiver um registro ela só possui o coordenador
		if (size <= 1) {
			return null;
		}

		// filtra todos os processos da lista que não são o coordenador
		Coordinator coordinator = LogicRing.getCoordinator();
		activeProcesslist = activeProcesslist
				.stream()
				.filter(process -> !process.equals(coordinator))
				.collect(Collectors.toList());

		// pegar aleatoriamente um processo ativo da lista
		size = activeProcesslist.size();
		int randomIndex = new Random().nextInt(size);
		return activeProcesslist.get(randomIndex);
	}

}
