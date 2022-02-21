package servidor;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorArchivos implements Runnable {

    private int PUERTO = 3888;

    public ServidorArchivos() {

    }

    @Override
    public void run() {
        try {

            System.out.println("Servidor iniciado");
            System.out.println("Esperando consultas de clientes\n");
            DatagramSocket datagramServidor = new DatagramSocket(this.PUERTO);
            while (true) {
                byte[] pregunta = new byte[1024];
                DatagramPacket paquetePreguntaArchivo = new DatagramPacket(pregunta,
                        pregunta.length);
                datagramServidor.receive(paquetePreguntaArchivo);
                System.out.print("Se recibio la consulta del cliente: ");

                int puertoCliente = paquetePreguntaArchivo.getPort();
                InetAddress direccionCliente = paquetePreguntaArchivo.getAddress();
                System.out.println("" + puertoCliente + " - " + direccionCliente);

                System.out.print("Se manda el archivo: ");
                String mensajeCliente = new String(paquetePreguntaArchivo.getData());
                System.out.println(mensajeCliente + "\n");
                
                File archivoSolicitado = new File(mensajeCliente);
                String txtArchivo = archivoSolicitado.toString();
                byte[] archivoBytes = new byte[1024];
                archivoBytes = txtArchivo.getBytes();
                DatagramPacket paqueteArchivo = new DatagramPacket(archivoBytes,
                        archivoBytes.length,
                        paquetePreguntaArchivo.getAddress(),
                        paquetePreguntaArchivo.getPort());
                datagramServidor.send(paqueteArchivo);
            }

        } catch (SocketException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
