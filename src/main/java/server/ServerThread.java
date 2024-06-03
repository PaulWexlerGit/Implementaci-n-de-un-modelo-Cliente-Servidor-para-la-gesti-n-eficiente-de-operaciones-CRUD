package server;

import common.Answer;
import common.Operation;
import utils.Config;
import utils.KeyGeneratorClass;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

class ServerThread extends Thread {

    private Socket socketCliente;
    private int id;
    private KeyGeneratorClass claveObj;
    private boolean enTransaccion;
    private boolean haHabidoExcepcion = false;

    public ServerThread(Socket cliente, int id, KeyGeneratorClass claveObj) {
        this.socketCliente = cliente;
        this.id = id;
        this.claveObj = claveObj;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        enTransaccion = false;
        do {
            try {
                ois = new ObjectInputStream(socketCliente.getInputStream());
                SealedObject sealedObject = (SealedObject) ois.readObject();
                Cipher cipher = Cipher.getInstance(Config.ALGORITMO_KEYGEN);
                cipher.init(Cipher.DECRYPT_MODE, claveObj.getClave());
                Answer answer = new Answer();
                Operation operation = (Operation) sealedObject.getObject(cipher);
                switch (operation.getOperacion()) {
                    case "create":
                        try {
                            Object object = ServerDAO.create(operation.getObject(), operation.getAutocommit());
                            answer.setStatus("OK");
                            answer.setObject(object);
                            answer.setOperacion("create");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("create");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "readList":
                        try {
                            List<Object> listObjects = ServerDAO.readList(operation.getObject());
                            answer.setStatus("OK");
                            answer.setObject(listObjects);
                            answer.setOperacion("readList");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("readList");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "readObject":
                        try {
                            Object object = ServerDAO.readObject(operation.getObject());
                            answer.setStatus("OK");
                            answer.setObject(object);
                            answer.setOperacion("readObject");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("readObject");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "update":
                        try {
                            Object object = ServerDAO.update(operation.getObject(), operation.getAutocommit());
                            answer.setStatus("OK");
                            answer.setObject(object);
                            answer.setOperacion("update");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("update");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "transaction":
                        try {
                            enTransaccion = true;
                            ServerDAO.transaction();
                            answer.setStatus("OK");
                            answer.setObject(null);
                            answer.setOperacion("transaction");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("transaction");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "commit":
                        try {
                            ServerDAO.commit();
                            answer.setStatus("OK");
                            answer.setObject(null);
                            answer.setOperacion("commit");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            enTransaccion = false;
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("commit");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                    case "delete":
                        try {
                            ServerDAO.delete(operation.getObject(), operation.getAutocommit());
                            answer.setStatus("OK");
                            answer.setObject(null);
                            answer.setOperacion("delete");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            answer.setStatus("KO");
                            answer.setObject(e);
                            answer.setOperacion("delete");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(answer, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion = true;
                        }
                        break;
                }
            } catch (Exception ex) {
                System.err.println("Salida de forma abrupta del hilo " + id);
                haHabidoExcepcion = true;
//                break;
            }
        } while (enTransaccion && !haHabidoExcepcion);
        if (ois != null) {
            try {
                ois.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (oos != null) {
            try {
                oos.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (socketCliente != null) {
            try {
                socketCliente.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
