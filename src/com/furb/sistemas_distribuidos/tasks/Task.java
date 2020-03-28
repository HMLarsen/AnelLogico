package com.furb.sistemas_distribuidos.tasks;

import java.util.TimerTask;

import com.furb.sistemas_distribuidos.util.Logger;

/**
 * Abstra��o respons�vel pelas tasks do ambiente distribu�do.
 */
public abstract class Task extends TimerTask {

	/**
	 * Envia mensagens ao logger principal do sistema distribu�do.
	 * 
	 * @param message mensagem a ser exibida
	 */
	public void logOut(String message) {
		Logger.log(message);
	}

}
