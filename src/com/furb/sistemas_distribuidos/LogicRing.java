package com.furb.sistemas_distribuidos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

import com.furb.sistemas_distribuidos.model.Coordinator;
import com.furb.sistemas_distribuidos.model.Process;
import com.furb.sistemas_distribuidos.task.CoordinatorRequestTask;
import com.furb.sistemas_distribuidos.task.CreateProcessorTask;
import com.furb.sistemas_distribuidos.task.InactivateCoordinatorTask;
import com.furb.sistemas_distribuidos.task.InactivateProcessTask;
import com.furb.sistemas_distribuidos.utils.Logger;

/**
 * Representa��o de um sistema distribu�do baseado no algoritmo Anel L�gico.
 */
public class LogicRing {

	// times
	private static final double SPEED_PERCENT = 1; // quanto menor mais r�pido
	private static final int CREATE_PROCESSOR_TASK_TIME = 30 * 1000;
	private static final int COORDINATOR_REQUEST_TASK_TIME = 25 * 1000;
	private static final int INACTIVATE_COORDINATOR_TASK_TIME = 100 * 1000;
	private static final int INACTIVATE_PROCESSOR_TASK_TIME = 80 * 1000;

	// controllers
	private static volatile List<Process> activeProcessList = new ArrayList<>();
	private static volatile Coordinator coordinator;

	/**
	 * Inicia a ambientaliza��o do sistema distribu�do.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CreateProcessorTask processorTask = new CreateProcessorTask();
		CoordinatorRequestTask coordinatorTask = new CoordinatorRequestTask();
		InactivateCoordinatorTask inactivateCoordinatorTask = new InactivateCoordinatorTask();
		InactivateProcessTask inactivateProcessTask = new InactivateProcessTask();

		Timer timer = new Timer();
		timer.schedule(processorTask, 0, getSpeedValue(CREATE_PROCESSOR_TASK_TIME));
		timer.schedule(coordinatorTask, getSpeedValue(COORDINATOR_REQUEST_TASK_TIME),
				getSpeedValue(COORDINATOR_REQUEST_TASK_TIME));
		timer.schedule(inactivateCoordinatorTask, getSpeedValue(INACTIVATE_COORDINATOR_TASK_TIME),
				getSpeedValue(INACTIVATE_COORDINATOR_TASK_TIME));
		timer.schedule(inactivateProcessTask, getSpeedValue(INACTIVATE_PROCESSOR_TASK_TIME),
				getSpeedValue(INACTIVATE_PROCESSOR_TASK_TIME));
	}

	/**
	 * @param time tempo a ser convertido
	 * @return ajusta o tempo passado com o percentual de rapidez das tarefas
	 */
	private static int getSpeedValue(int time) {
		return (int) ((int) time * (SPEED_PERCENT / 100));
	}

	/**
	 * Inicia uma elei��o no ambiente para definir um novo coordenador.<br>
	 * M�todo sincronizado para n�o haver elei��es ao mesmo tempo.
	 * 
	 * @param processId id processo que fez a requisi��o da elei��o
	 */
	public static synchronized void makeElection(long processId) {
		if(coordinator != null) {
			return;
		}
		
		int index = activeProcessList.size() - 1;
		Coordinator newCoordinator = new Coordinator(activeProcessList.get(index));
		setCoordinator(newCoordinator);

		StringBuilder builder = new StringBuilder();
		builder.append("ELEI��O iniciada pelo processo " + processId + ":\n");
		builder.append("Processo " + getCoordinator().getId() + " � o novo coordenador.\n");
		builder.append("Processos ativos: " + Arrays.toString(activeProcessList.toArray()));
		Logger.log(builder.toString());
	}

	/**
	 * Remove o processo da lista de processos ativos para todo o ambiente
	 * distribu�do.
	 * 
	 * @param process processo a ser inativado
	 */
	public static void deactivateProcess(Process process) {
		activeProcessList.remove(process);
		Logger.log("Processo " + process.getId() + " inativado.");
	}

	/**
	 * Remove o coordenador atual da lista de processos ativos e da vari�vel fixa
	 * para todo o ambiente distribu�do.
	 */
	public static void deactivateCoordinator() {
		long coordinatorId = coordinator.getId();
		activeProcessList.remove(coordinator);
		coordinator = null;
		Logger.log("Coordenador " + coordinatorId + " inativado.");
	}

	/**
	 * Adiciona um processo na lista de processos ativos.
	 * 
	 * @param process processo a ser adicionado
	 */
	public static void addProcess(Process process) {
		activeProcessList.add(process);
	}

	public static List<Process> getActiveProcesslist() {
		return activeProcessList;
	}

	public static Coordinator getCoordinator() {
		return coordinator;
	}

	public static void setCoordinator(Process process) {
		LogicRing.coordinator = new Coordinator(process);
	}

}
