package comun;

import java.io.Serializable;

public class Operacion implements Serializable {
    private String operacion;
    private Object object;

    private Boolean autocommit;

    public Operacion() {
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
