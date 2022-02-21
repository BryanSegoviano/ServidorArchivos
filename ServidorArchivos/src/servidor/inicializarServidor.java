package servidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class inicializarServidor {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(new ServidorArchivos());
    }

}
