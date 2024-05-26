package server;

import java.io.Serializable;

public class MyException extends Exception implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public MyException(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
}
