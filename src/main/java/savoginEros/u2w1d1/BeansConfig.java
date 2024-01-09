package savoginEros.u2w1d1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import savoginEros.u2w1d1.entities.*;

@Configuration // annotazione obbligatoria per il metodo coi beans
public class BeansConfig {

    @Bean // obbligatorio per definire che questo metodo dovà tornare un oggetto gestito da Spring
    FrontEndStudent getFES() {
        return new FrontEndStudent("Aldo");
    }
    @Bean
    BackEndStudent getBES() {
        return new BackEndStudent("Giovanni");
    }
    @Bean
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
    Interviewer getInterviewer(FrontEndStudent student) {
        return new Interviewer(student);
        // ma come fa a sapere che si riferisce proprio a quel metodo preciso di tipo FrontEndStudent?
        // Beh nella lezione di oggi non è ancora prevista soluzione, mi pare.
        // se qui avessi messo come parametro IStudent, application mi avrebbe dato errore perché
        // trova 3 possibili risultati e non sa quale scegliere!
        // Guarda sopra, abbiamo 3 bean che implementano tutti e 3 l'interfaccia IStudent!
        // quindi il nostro metodo dovrà specificarne solo una di classe con implementazione di IStudent
        // Oppure dovrei avere un solo bean con implementata l'interfaccia!
    }

}
