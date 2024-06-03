package common;

import java.io.Serializable;

public class Operation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String operacion;
    private Object object;

    private Boolean autocommit;

    public Operation() {
        autocommit = true;
    }

    public Boolean getAutocommit() {
        return autocommit;
    }

    public void setAutocommit(Boolean autocommit) {
        this.autocommit = autocommit;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
