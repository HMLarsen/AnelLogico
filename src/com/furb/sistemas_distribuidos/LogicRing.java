package com.furb.sistemas_distribuidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.tasks.CoordinatorRequestTask;
import com.furb.sistemas_distribuidos.tasks.CreateProcessorTask;
import com.furb.sistemas_distribuidos.tasks.InactivateCoordinatorTask;
import com.furb.sistemas_distribuidos.tasks.InactivateProcessTask;
import com.furb.sistemas_distribuidos.util.Logger;
import com.furb.sistemas_distribuidos.util.ProcessSelector;

/**
 * Representa��o de um sistema distribu�do baseado no algoritmo Anel L�gico.
 */
public class LogicRing {

	// times
	private static final int CREATE_PROCESSOR_TASK_TIME = 30 * 1000;
	private static final int COORDINATOR_REQUEST_TASK_TIME = 25 * 1000;
	private static final int INACTIVATE_COORDINATOR_TASK_TIME = 100 * 1000;
	private static final int INACTIVATE_PROCESSOR_TASK_TIME = 80 * 1000;

	// controllers
	private static final Timer timer = new Timer();
	private static final List<Process> processList = new ArrayList<>();

	/**
	 * Inicia a ambientaliza��o do sistema distribu�do.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CreateProcessorTask processorTask = new CreateProcessorTask();
		InactivateCoordinatorTask inactivateCoordinatorTask = new InactivateCoordinatorTask();
		InactivateProcessTask inactivateProcessTask = new InactivateProcessTask();

		timer.schedule(processorTask, 0, CREATE_PROCESSOR_TASK_TIME);
		timer.schedule(inactivateCoordinatorTask, INACTIVATE_COORDINATOR_TASK_TIME, INACTIVATE_COORDINATOR_TASK_TIME);
		timer.schedule(inactivateProcessTask, INACTIVATE_PROCESSOR_TASK_TIME, INACTIVATE_PROCESSOR_TASK_TIME);
	}

	/**
	 * Inicia o lapse de requests do processo ao coordenador.
	 * 
	 * @param process processo que requisitar� ao coordenador
	 */
	public static void initProcessRequestLapse(Process process) {
		CoordinatorRequestTask coordinatorTask = new CoordinatorRequestTask(process);
		timer.schedule(coordinatorTask, COORDINATOR_REQUEST_TASK_TIME, COORDINATOR_REQUEST_TASK_TIME);
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
	 * Se o coordenador n�o estiver mais dispon�vel ser� iniciada uma elei��o.
	 * 
	 * @param process processo que fez o request
	 */
	public static void handleRequest(Process process) {
		Process coordinator = ProcessSelector.getActiveCoordinator();
		if (coordinator != null) {
			Logger.log("Requisi��o recebida pelo coordenador " + coordinator.getId() + ".");
		} else {
			makeElection();
			handleRequest(process);
		}
	}

	/**
	 * Inicia uma elei��o no ambiente para definir um novo coordenador.
	 */
	public static synchronized void makeElection() {
		// TODO implementar corretamente o algoritmo da elei��o
		// e documentar corretamente o m�todo
		Process randomProcess = ProcessSelector.getRandomActiveNotCoordinator();
		randomProcess.setCoordinator(true);
		Logger.log("Processo " + randomProcess.getId() + " � o novo coordenador.");
	}

	public List<Process> getProcessList() {
		return processList;
	}

	public static List<Process> getProcesslist() {
		return processList;
	}

}
