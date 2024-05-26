package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Utils {
	public static KeyGeneratorClass leerClaveSimetrica() throws Exception {
		KeyGeneratorClass clave = null;
		try (FileInputStream fis = new FileInputStream(Config.KEY_FILE_NAME);
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			clave = (KeyGeneratorClass) ois.readObject();
		} catch (Exception ex) {
			throw ex;
		}
		return clave;
	}

	public static byte[] stringToByteArray(String cadena) {
		return cadena.getBytes();
	}

	public static String byteArrayToString(byte[] bytes) {
		return new String(bytes);
	}

	public static byte[] cifrar(byte[] bytes, KeyGeneratorClass keyObj) throws Exception {
		try {
			Cipher c = Cipher.getInstance(Config.TRANSFORMATION);
			c.init(Cipher.ENCRYPT_MODE, keyObj.getClave());
			return c.doFinal(bytes);
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		} catch (NoSuchPaddingException ex) {
			throw new Exception(ex.getMessage());
		} catch (IllegalBlockSizeException ex) {
			throw new Exception(ex.getMessage());
		} catch (BadPaddingException ex) {
			throw new Exception(ex.getMessage());
		} catch (InvalidKeyException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public static byte[] descifrar(byte[] bytes, KeyGeneratorClass keyObj) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(Config.TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, keyObj.getClave());
			byte[] buffDescifrados = cipher.doFinal(bytes);
			return buffDescifrados;
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		} catch (NoSuchPaddingException ex) {
			throw new Exception(ex.getMessage());
		} catch (InvalidKeyException ex) {
			throw new Exception(ex.getMessage());
		} catch (IllegalBlockSizeException ex) {
			throw new Exception(ex.getMessage());
		} catch (BadPaddingException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public static byte[] getHash(String algoritmo, byte[] bytes) throws Exception {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance(algoritmo);
			md.update(bytes);
			byte resumen[] = md.digest();
			return resumen;
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception("Algoritmo Hash no encontrado");
		}
	}

	public static String Hexadecimal(byte[] resumen) {
		String hex = "";
		for (byte element : resumen) {
			String h = Integer.toHexString(element & 0xFF) + ":";
			if (h.length() == 1) {
				hex += "0";
			}
			hex += h;
		}
		return hex.toUpperCase();
	}// Hexadecimal

	// para ficheros:
	public static void grabarFichero(String nombre, byte[] ficheroBytes) throws Exception {
		File ficheroCifrado;
		BufferedOutputStream fichSalida = null;
		try {
			ficheroCifrado = new File(nombre);
			fichSalida = new BufferedOutputStream(new FileOutputStream(ficheroCifrado));
			fichSalida.write(ficheroBytes);
			fichSalida.flush();
		} catch (FileNotFoundException ex) {
			throw new Exception(ex.getMessage());
		} catch (IOException ex) {
			throw new Exception(ex.getMessage());
		} finally {
			if (fichSalida != null) {
				try {
					fichSalida.close();
				} catch (IOException ex) {
					throw new Exception(ex.getMessage());
				}
			}
		}
	}
}
/*
 * KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
 * keyGenerator.init(128); // Puedes elegir 192 o 256 bits según lo necesites
 * SecretKey secretKey = keyGenerator.generateKey();
 *
 * 1. Cifra el objeto Persona antes de enviarlo. Para ello, serializa el objeto
 * y luego cifra el flujo de bytes resultante.
 *
 * import javax.crypto.Cipher; import javax.crypto.SealedObject;
 *
 * Cipher cipher = Cipher.getInstance("AES"); cipher.init(Cipher.ENCRYPT_MODE,
 * secretKey); SealedObject sealedObject = new SealedObject(persona, cipher);
 *
 * 1. Envía la clave simétrica y el objeto cifrado a través del socket. Deberás
 * enviar la clave de forma segura, posiblemente utilizando cifrado asimétrico o
 * un intercambio de claves seguro como Diffie-Hellman.
 *
 * import java.io.ObjectOutputStream; import java.net.Socket;
 *
 * try (Socket socket = new Socket("direccion_del_servidor", puerto)) {
 * ObjectOutputStream outputStream = new
 * ObjectOutputStream(socket.getOutputStream());
 *
 * // Envía la clave simétrica cifrada con la clave pública del receptor aquí
 *
 * // Envía el objeto cifrado outputStream.writeObject(sealedObject); }
 *
 * 1. En el lado del receptor, descifra la clave simétrica y luego utiliza esa
 * clave para descifrar el objeto Persona.
 *
 * import javax.crypto.Cipher; import java.io.ObjectInputStream;
 *
 * try (Socket socket = new Socket("direccion_del_servidor", puerto)) {
 * ObjectInputStream inputStream = new
 * ObjectInputStream(socket.getInputStream());
 *
 * // Recibe y descifra la clave simétrica aquí
 *
 * SealedObject sealedObject = (SealedObject) inputStream.readObject(); Cipher
 * cipher = Cipher.getInstance("AES"); cipher.init(Cipher.DECRYPT_MODE,
 * secretKey);
 *
 * Persona persona = (Persona) sealedObject.getObject(cipher); }
 *
 * Recuerda que la seguridad de la transmisión depende de cómo manejes las
 * claves y de la fortaleza del algoritmo de cifrado que elijas. Además,
 * asegúrate de manejar adecuadamente las excepciones y cerrar los recursos en
 * bloques try-with-resources o en un bloque finally.
 */