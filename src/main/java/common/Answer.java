package common;

import java.io.Serializable;

public class Answer implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Object object;
	private String status;
	private String operacion;

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Answer() {
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
