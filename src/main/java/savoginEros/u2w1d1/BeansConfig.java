package savoginEros.u2w1d1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import savoginEros.u2w1d1.entities.*;

@Configuration // annotazione obbligatoria per il metodo coi beans
@PropertySource("application.properties") // serve per specificare come si chiama il file con le variabili d'ambiente
public class BeansConfig {

    @Bean // obbligatorio per definire che questo metodo dovà tornare un oggetto gestito da Spring
    //@Primary  // in caso di ambiguità nel bean getInterviewer prenderà questo bean!
    FrontEndStudent getFES(@Value("${student.name}") String name) { // @Value mi permette di leggere un valore da application.properties e passarlo come parametro
        return new FrontEndStudent(name);
    }
    @Bean
    //@Primary // in caso di ambiguità nel bean getInterviewer prenderà questo bean! Oppure ambiguità tra Component-bean e questi bean.
    BackEndStudent getBES() {
        return new BackEndStudent("Giovanni");
    }
    @Bean
    @Primary
    //@Scope("prototype")  // modificando lo scope in prototype, a ogni invocazione del metodo getBean() creerò una nuova istanza
    FullStackStudent getFSS() {
        return new FullStackStudent("Giacomo");
    }
    // Possiamo anche fare metodi bean che ritornano dati più semplici come stringhe o interi
    @Bean(name = "nomeCustomBeanGetString")  // posso dare un nome alternativo al bean, che utilizzerò nel getBean al posto del nome del metodo
    String getString() {
        return "prova con stringa";
    }
    @Bean
    int getNum() {
        return 34;
    }
    @Bean
    Interviewer getInterviewer(IStudent student) { // Questo bean è UGUALE al Component bean, interviewer_component
        return new Interviewer(student);
        // Quando Spring vede che abbiamo bisogno di una dipendenza applica il principio della Dependency Injection (DI),
        // cioè la dipendenza viene iniettata, quindi riceverò da "fuori" l'oggetto

        // se qui avessi messo come parametro IStudent, application mi avrebbe dato errore perché
        // trova 3 possibili risultati e non sa quale scegliere!
        // Guarda sopra, abbiamo 3 bean che implementano tutti e 3 l'interfaccia IStudent!
        // Quindi il nostro metodo dovrà specificarne solo una di classe con implementazione di IStudent
        // Oppure dovrei avere un solo bean con implementata l'interfaccia!
        // OPPURE nel caso ci siano ambiguità posso aggiungere l'annotazione @Primary a un bean che voglio che si riferisca (solo)

        // Ora proviamo a levare @Primary da un bean dei 3 studenti e mettiamo @Qualifier nel costruttore del Component Interviewer
        // Quindi mi creerà un bean prendendo un altro bean, cioè il bean "getFSS", e qui tutto bene
        // MA c'è un problema, se non commento il bean getInterviewer() avrò il solito problema che non sa quale
        // bean di tipo IStudent (e quindi dei 3 tipi di studente) scegliere.
        // RICORDATI che si possono avere più bean che tornano la stessa cosa, l'importante è che SPRING riesca a risolverne le dipendenze
        // e quindi che non ci siano conflitti
    }

}
