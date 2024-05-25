package cliente;

import comun.Operacion;
import comun.Respuesta;
import utils.Config;
import utils.GenerarClave;
import utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteDAO {
    private static GenerarClave clave;
    private static Socket socket;
    private static Boolean enTransaccion;
    private static boolean autocommit = true;

    static {
        clave = null;
    }

    public static void transaction() throws Exception {
        autocommit = false;
        Operacion operacion = new Operacion();
        operacion.setOperacion("transaction");
        operacion.setObject(null);
        envia(operacion);
    }

    public static void commit() throws Exception {
        autocommit = true;
        Operacion operacion = new Operacion();
        operacion.setOperacion("commit");
        operacion.setObject(null);
        envia(operacion);
    }

    public static Object create(Object object) throws Exception {// ,Boolean autocommit) throws Exception {
        Operacion operacion = new Operacion();
        operacion.setOperacion("create");
        operacion.setObject(object);
        operacion.setAutocommit(autocommit);
        Object respuesta=envia(operacion);
        return respuesta;
    }

//    public static void create(Object object) throws Exception {
//        create(object, true);
//    }

    public static Object readList(Object hql) throws Exception {
        Operacion operacion = new Operacion();
        operacion.setOperacion("readList");
        operacion.setObject(hql);
        return envia(operacion);
    }

    public static Object readObject(Object hql) throws Exception {
        Operacion operacion = new Operacion();
        operacion.setOperacion("readObject");
        operacion.setObject(hql);
        return envia(operacion);
    }

    public static Object update(Object object) throws Exception {// Boolean autocommit) throws Exception {
        Operacion operacion = new Operacion();
        operacion.setOperacion("update");
        operacion.setObject(object);
        operacion.setAutocommit(autocommit);
        return envia(operacion);
    }

//    public static Object update(Object object) throws Exception {
//        return update(object, true);
//    }

    public static void delete(Object object) throws Exception {//, Boolean autocommit) throws Exception {
        Operacion operacion = new Operacion();
        operacion.setOperacion("delete");
        operacion.setObject(object);
        operacion.setAutocommit(autocommit);
        envia(operacion);
    }
//    public static void delete(Object object) throws Exception {
//        delete(object, true);
//    }

    private static Object envia(Operacion operacion) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        if (clave == null) {
            try {
                clave = Utils.leerClaveSimetrica(); //leo la clave y la guardo en clave
            } catch (Exception ex) {
                throw ex;
            }
        }
        if (operacion.getOperacion().equals("transaction")) {
            enTransaccion = true;
            socket = new Socket(Config.SERVIDOR, Config.PUERTO);
        } else {
            if (socket == null || socket.isClosed()) {
                enTransaccion = false;
                socket = new Socket(Config.SERVIDOR, Config.PUERTO);
            }
        }
        try {
            SealedObject sealedObject;
            Cipher cipher = Cipher.getInstance(Config.ALGORITMO_KEYGEN);
            cipher.init(Cipher.ENCRYPT_MODE, clave.getClave());

            sealedObject = new SealedObject(operacion, cipher);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(sealedObject);

            ois = new ObjectInputStream(socket.getInputStream());
            sealedObject = (SealedObject) ois.readObject();
            cipher.init(Cipher.DECRYPT_MODE, clave.getClave());
            Respuesta respuesta = (Respuesta) sealedObject.getObject(cipher);
            switch (respuesta.getStatus()) {
                case "KO":
                    if(autocommit == false){
                        autocommit = true;
                        enTransaccion= false;
                    }
                    throw (Exception) respuesta.getObject();
                case "OK":
                    switch (respuesta.getOperacion()) {
                        case "update":
                        case "read":
                        case "readList":
                        case "readObject":
                        case "create":
                            return respuesta.getObject();
                    }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (operacion.getOperacion().equals("commit") && (socket != null)) {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
                socket.close();
                socket = null;
                enTransaccion = false;
            } else if (enTransaccion == false) {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
                socket.close();
                socket = null;
            }
        }
        return null;
    }
}