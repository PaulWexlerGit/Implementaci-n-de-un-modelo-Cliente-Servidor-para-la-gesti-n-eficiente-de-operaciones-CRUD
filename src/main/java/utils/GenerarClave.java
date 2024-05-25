package utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class GenerarClave implements Serializable {
    private SecretKey clave;

    public Key getClave() {
        return clave;
    }

    public void setClave(SecretKey clave) {
        this.clave = clave;
    }

    public GenerarClave() {

    }

    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = null;
        File ficheroSecret = null;
        GenerarClave secretKeyObj = null;
        KeyGenerator keyGen;
//
        try {
            secretKeyObj = new GenerarClave();
            keyGen = KeyGenerator.getInstance(Config.ALGORITMO_KEYGEN);
            keyGen.init(Config.KEY_SIZE);
            SecretKey sk = keyGen.generateKey();
            secretKeyObj.setClave(sk);
            //setSecretKey(sk);
            ficheroSecret = new File(Config.KEY_FILE_NAME);
            oos = new ObjectOutputStream(new FileOutputStream(ficheroSecret));
            oos.writeObject(secretKeyObj);
            return;
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage() + " Error de Algoritmo no encontrado");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage() + " Error de entrada o salida");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                throw new Exception(ex.getMessage() + " claveObjeto es null");
            }
        }
    }
}
