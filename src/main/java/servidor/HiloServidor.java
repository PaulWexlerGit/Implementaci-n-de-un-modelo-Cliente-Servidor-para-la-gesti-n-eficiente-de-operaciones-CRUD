package servidor;

import comun.ObjetoEnvio;
import comun.Operacion;
import comun.Respuesta;
import utils.Config;
import utils.GenerarClave;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

class HiloServidor extends Thread {

    private Socket socketCliente;
    private int id;
    private GenerarClave claveObj;
    private boolean enTransaccion;
    private boolean haHabidoExcepcion = false;

    public HiloServidor(Socket cliente, int id, GenerarClave claveObj) {
        this.socketCliente = cliente;
        this.id = id;
        this.claveObj = claveObj;
    }

    @Override
    public void run() {
        ObjetoEnvio oe;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        enTransaccion = false;
        do {
            try {
                ois = new ObjectInputStream(socketCliente.getInputStream());
                SealedObject sealedObject = (SealedObject) ois.readObject();
                Cipher cipher = Cipher.getInstance(Config.ALGORITMO_KEYGEN);
                cipher.init(Cipher.DECRYPT_MODE, claveObj.getClave());
                Respuesta respuesta = new Respuesta();
                Operacion operacion = (Operacion) sealedObject.getObject(cipher);
                switch (operacion.getOperacion()) {
                    case "create":
                        try {
                            Object object = ServidorDAO.create(operacion.getObject(), operacion.getAutocommit());
                            respuesta.setStatus("OK");
                            respuesta.setObject(object);
                            respuesta.setOperacion("create");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("create");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "readList":
                        try {
                            List<Object> listObjects = ServidorDAO.readList(operacion.getObject());
                            respuesta.setStatus("OK");
                            respuesta.setObject(listObjects);
                            respuesta.setOperacion("readList");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("readList");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "readObject":
                        try {
                            Object object = ServidorDAO.readObject(operacion.getObject());
                            respuesta.setStatus("OK");
                            respuesta.setObject(object);
                            respuesta.setOperacion("readObject");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("readObject");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "update":
                        try {
                            Object object = ServidorDAO.update(operacion.getObject(), operacion.getAutocommit());
                            respuesta.setStatus("OK");
                            respuesta.setObject(object);
                            respuesta.setOperacion("update");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("update");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "transaction":
                        try {
                            enTransaccion = true;
                            ServidorDAO.transaction();
                            respuesta.setStatus("OK");
                            respuesta.setObject(null);
                            respuesta.setOperacion("transaction");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("transaction");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "commit":
                        try {
                            ServidorDAO.commit();
                            respuesta.setStatus("OK");
                            respuesta.setObject(null);
                            respuesta.setOperacion("commit");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            enTransaccion = false;
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("commit");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                    case "delete":
                        try {
                            ServidorDAO.delete(operacion.getObject(), operacion.getAutocommit());
                            respuesta.setStatus("OK");
                            respuesta.setObject(null);
                            respuesta.setOperacion("delete");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                        } catch (Exception e) {
                            respuesta.setStatus("KO");
                            respuesta.setObject(e);
                            respuesta.setOperacion("delete");
                            cipher.init(Cipher.ENCRYPT_MODE, claveObj.getClave());
                            sealedObject = new SealedObject(respuesta, cipher);
                            oos = new ObjectOutputStream(socketCliente.getOutputStream());
                            oos.writeObject(sealedObject);
                            haHabidoExcepcion= true;
                        }
                        break;
                }
            } catch (Exception ex) {
                System.err.println("Salida de forma abrupta del hilo " + id);
                haHabidoExcepcion = true;
//                break;
            }
        }
        while (enTransaccion && !haHabidoExcepcion);
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
        if (socketCliente!=null) {
            try {
                socketCliente.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
