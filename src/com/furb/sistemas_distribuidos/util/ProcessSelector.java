package com.furb.sistemas_distribuidos.util;

import java.util.Random;

import com.furb.sistemas_distribuidos.LogicRing;
import com.furb.sistemas_distribuidos.model.Process;

/**
 * Classe utilit�ria para selecionar processos no ambiente distribu�do.
 */
public class ProcessSelector {

	/**
	 * @return um processo ativo aleat�rio (n�o sendo o coordenador)
	 */
	public static Process getRandomActiveNotCoordinator() {
		Random random = new Random();
		int size = LogicRing.getProcesslist().size();
		return LogicRing.getProcesslist().stream()
				.filter(item -> item.isActive() && !item.isCoordinator())
				.skip(random.nextInt(size - 1))
				.findFirst()
				.get();
	}

	/**
	 * @return coordenador atual e ativo do ambiente distribu�do
	 */
	public static Process getActiveCoordinator() {
		return LogicRing.getProcesslist().stream()
				.filter(process -> process.isActive() && process.isCoordinator())
				.findFirst()
				.orElse(null);
	}

}
