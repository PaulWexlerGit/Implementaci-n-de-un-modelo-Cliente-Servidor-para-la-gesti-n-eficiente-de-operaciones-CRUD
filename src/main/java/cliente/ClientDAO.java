package cliente;

import common.Answer;
import common.Operation;
import utils.Config;
import utils.KeyGeneratorClass;
import utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientDAO {
    private static KeyGeneratorClass clave;
    private static Socket socket;
    private static Boolean enTransaccion;
    private static boolean autocommit = true;

    static {
        clave = null;
    }

    public static void transaction() throws Exception {
        autocommit = false;
        Operation operation = new Operation();
        operation.setOperacion("transaction");
        operation.setObject(null);
        envia(operation);
    }

    public static void commit() throws Exception {
        autocommit = true;
        Operation operation = new Operation();
        operation.setOperacion("commit");
        operation.setObject(null);
        envia(operation);
    }
    public static void rollback() throws Exception {
        autocommit = true;
        Operation operation = new Operation();
        operation.setOperacion("rollback");
        operation.setObject(null);
        envia(operation);
    }

    public static Object create(Object object) throws Exception {// ,Boolean autocommit) throws Exception {
        Operation operation = new Operation();
        operation.setOperacion("create");
        operation.setObject(object);
        operation.setAutocommit(autocommit);
        Object respuesta = envia(operation);
        return respuesta;
    }

//    public static void create(Object object) throws Exception {
//        create(object, true);
//    }

    public static Object readList(Object hql) throws Exception {
        Operation operation = new Operation();
        operation.setOperacion("readList");
        operation.setObject(hql);
        return envia(operation);
    }

    public static Object readObject(Object hql) throws Exception {
        Operation operation = new Operation();
        operation.setOperacion("readObject");
        operation.setObject(hql);
        return envia(operation);
    }

    public static Object update(Object object) throws Exception {// Boolean autocommit) throws Exception {
        Operation operation = new Operation();
        operation.setOperacion("update");
        operation.setObject(object);
        operation.setAutocommit(autocommit);
        return envia(operation);
    }

//    public static Object update(Object object) throws Exception {
//        return update(object, true);
//    }

    public static void delete(Object object) throws Exception {// , Boolean autocommit) throws Exception {
        Operation operation = new Operation();
        operation.setOperacion("delete");
        operation.setObject(object);
        operation.setAutocommit(autocommit);
        envia(operation);
    }
//    public static void delete(Object object) throws Exception {
//        delete(object, true);
//    }

    private static Object envia(Operation operation) throws Exception {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        if (clave == null) {
            try {
                clave = Utils.leerClaveSimetrica(); // leo la clave y la guardo en clave
            } catch (Exception ex) {
                throw ex;
            }
        }
        if (operation.getOperacion().equals("transaction")) {
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

            sealedObject = new SealedObject(operation, cipher);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(sealedObject);

            ois = new ObjectInputStream(socket.getInputStream());
            sealedObject = (SealedObject) ois.readObject();
            cipher.init(Cipher.DECRYPT_MODE, clave.getClave());
            Answer answer = (Answer) sealedObject.getObject(cipher);
            switch (answer.getStatus()) {
                case "KO":
                    if (!autocommit) {
                        autocommit = true;
                        enTransaccion = false;
                    }
                    throw (Exception) answer.getObject();
                case "OK":
                    switch (answer.getOperacion()) {
                        case "update":
                        case "read":
                        case "readList":
                        case "readObject":
                        case "create":
                            return answer.getObject();
                    }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (operation.getOperacion().equals("commit") && (socket != null)) {
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
            } else if (!enTransaccion) {
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