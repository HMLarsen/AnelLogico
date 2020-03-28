package com.furb.sistemas_distribuidos.tasks;

import java.util.TimerTask;

import com.furb.sistemas_distribuidos.util.Logger;

/**
 * Abstração responsável pelas tasks do ambiente distribuído.
 */
public abstract class Task extends TimerTask {

	/**
	 * Envia mensagens ao logger principal do sistema distribuído.
	 * 
	 * @param message mensagem a ser exibida
	 */
	public void logOut(String message) {
		Logger.log(message);
	}

}
