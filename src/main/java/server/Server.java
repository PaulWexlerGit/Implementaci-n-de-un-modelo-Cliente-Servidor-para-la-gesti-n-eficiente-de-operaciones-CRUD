package server;

import java.net.ServerSocket;
import java.net.Socket;

import utils.Config;
import utils.KeyGeneratorClass;
import utils.Utils;

public class Server {
	public static void main(String[] args) {
		Socket cliente;
		int id = 0;
		KeyGeneratorClass clave = null;
		try {
			clave = Utils.leerClaveSimetrica();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return;
		}
		try (ServerSocket ss = new ServerSocket(Config.PUERTO)) {
			do {
				cliente = ss.accept();
				id++;
				ServerThread hs = new ServerThread(cliente, id, clave);
				hs.start();
			} while (true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
