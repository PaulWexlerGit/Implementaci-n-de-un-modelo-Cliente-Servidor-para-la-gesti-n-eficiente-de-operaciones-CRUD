package servidor;

import comun.ObjetoEnvio;
import utils.Config;
import utils.GenerarClave;
import utils.Utils;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ObjetoEnvio oe = new ObjetoEnvio();
        Socket cliente;
        int id = 0;
        GenerarClave clave = null;
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
                HiloServidor hs = new HiloServidor(cliente, id, clave);
                hs.start();
            } while (true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
