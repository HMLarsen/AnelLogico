package com.furb.sistemas_distribuidos;

import java.util.LinkedList;
import java.util.Timer;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.tasks.CoordinatorRequestTask;
import com.furb.sistemas_distribuidos.tasks.CreateProcessorTask;
import com.furb.sistemas_distribuidos.tasks.InactivateCoordinatorTask;
import com.furb.sistemas_distribuidos.tasks.InactivateProcessTask;
import com.furb.sistemas_distribuidos.util.Logger;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Representação de um sistema distribuído baseado no algoritmo Anel Lógico.
 */
public class LogicRing {

	// times
	private static final int CREATE_PROCESSOR_TASK_TIME = 30 * 1000;
	private static final int COORDINATOR_REQUEST_TASK_TIME = 25 * 1000;
	private static final int INACTIVATE_COORDINATOR_TASK_TIME = 100 * 1000;
	private static final int INACTIVATE_PROCESSOR_TASK_TIME = 80 * 1000;

	// controllers
	private static final Timer timer = new Timer();
	private static final LinkedList<Process> processList = new LinkedList<>();
	private static final LinkedList<Process> processElectionList = new LinkedList<>();

	/**
	 * Inicia a ambientalização do sistema distribuído.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CreateProcessorTask processorTask = new CreateProcessorTask();
		CoordinatorRequestTask coordinatorTask = new CoordinatorRequestTask();
		InactivateCoordinatorTask inactivateCoordinatorTask = new InactivateCoordinatorTask();
		InactivateProcessTask inactivateProcessTask = new InactivateProcessTask();

		timer.schedule(processorTask, 0, CREATE_PROCESSOR_TASK_TIME);
		timer.schedule(coordinatorTask, COORDINATOR_REQUEST_TASK_TIME, COORDINATOR_REQUEST_TASK_TIME);
		timer.schedule(inactivateCoordinatorTask, INACTIVATE_COORDINATOR_TASK_TIME, INACTIVATE_COORDINATOR_TASK_TIME);
		timer.schedule(inactivateProcessTask, INACTIVATE_PROCESSOR_TASK_TIME, INACTIVATE_PROCESSOR_TASK_TIME);
	}

	/**
	 * Adiciona um processo na lista de processos.
	 * 
	 * @param process processo a ser adicionado
	 */
	public static void addProcess(Process process) {
		processList.add(process);
	}

	/**
	 * Gerencia os requests dos processos ao coordenador.<br>
	 * Se o coordenador não estiver mais disponível será iniciada uma eleição.
	 * 
	 * @param process processo que fez o request
	 */
	public static void handleRequest(Process process) {
		Process coordinator = ProcessSelector.getActiveCoordinator();
		if (coordinator != null) {
			Logger.log("Requisição recebida pelo coordenador " + coordinator.getId() + ".");
		} else {
			handleRequest(makeElection(process));
		}
	}

	/**
	 * Inicia uma eleição no ambiente para definir um novo coordenador.
	 */
	public static synchronized Process makeElection(Process process) {

		LinkedList<Process> processes = getProcessElectionlist();

		if (processes.contains(process)) {
			Process coordenador = ProcessSelector.makeElection();
			coordenador.setCoordinator(true);

			Logger.log("Processo " + coordenador.getId() + " é o novo coordenador.");
			return coordenador;
		} else if (process.isActive()) {
			processes.add(process);
		}

		return getProcesslist().get(getProcessElectionlist().indexOf(process) + 1); // REVER
	}

	public LinkedList<Process> getProcessList() {
		return processList;
	}

	public static LinkedList<Process> getProcesslist() {
		return processList;
	}

	public LinkedList<Process> getProcessElectionList() {
		return processElectionList;
	}

	public static LinkedList<Process> getProcessElectionlist() {
		return processElectionList;
	}

}
