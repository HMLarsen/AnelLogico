package com.furb.sistemas_distribuidos.utils;

import java.util.Comparator;

import com.furb.sistemas_distribuidos.model.Process;

/**
 * Comparador de processos a partir do id.
 */
public class ProcessIdComparator implements Comparator<Process> {

	@Override
	public int compare(Process o1, Process o2) {
		return Long.compare(o1.getId(), o2.getId());
	}
}
