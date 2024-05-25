package servidor;

import java.io.Serializable;

public class MyException extends Exception implements Serializable {
    private String mensaje;

    public MyException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
}
