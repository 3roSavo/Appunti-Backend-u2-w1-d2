package savoginEros.u2w1d1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Questa classe all'avvio dell'applicazione verrà eseguita per prima, così potremo per esempio inizializzare il database.
// Se non utilizzo l'annotazione Component, il runner non verrà avviato
// Essendo la classe annotata con Component, Spring la cercherà e ne creerà l' oggetto.
// Spring sa che questo oggetto implementa l'interfaccia CommandLineRunner e il relativo metodo,
// e all'avvio dell' applicazione farà partire il metodo run()
public class MyRunner implements CommandLineRunner { // questa interfaccia mi obbliga a utilizzare il metodo run
    @Override
    public void run(String... args) throws Exception {
        // Questo metodo verrà eseguito all'avvio dell'applicazione
        // È quindi comodo avere uno o più runner per inizializzare l'applicazione
        // Come ad esempio usarli per riempire il database, senza andare a sporcare il main
        System.out.println("SONO IL RUNNER");
    }
}
