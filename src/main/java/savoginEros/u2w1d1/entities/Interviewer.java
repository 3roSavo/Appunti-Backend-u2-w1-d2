package savoginEros.u2w1d1.entities;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("interviewer_component") // Utilizziamo il metodo 3 per ..... rendiamo boooooh questa classe
public class Interviewer {
    // ATTRIBUTI
    //@Autowired // Field Dependency Injection
    // Aggiungendo questa annotation in assenza del costruttore sottostante sarà risolta la dipendenza dell'attributo in automatico
    // Quindi non è implicita l'auto dipendenza degli attributi, qui il valore dell'attributo è trasmesso dal costruttore! MA se non
    // ci fosse il costruttore l'attributo non riuscirebbe a risolvere la dipendenza senza @Autowired!
    private IStudent student; // Dipendenza da una classe compatibile con IStudent

    // COSTRUTTORE
    // @Autowired Qua serve per effettuare la Constructor DI cioè dependency injection (ma è opzionale nel costruttore, cioè è implicito)
    // nel senso che è implicita che sia e quindi anche senza questa annotazione verrà risolta lo stesso la dipendenza
    public Interviewer(@Qualifier("getFSS") IStudent student) {
        // In caso di ambiguità (ci sono più Bean di tipo IStudent) posso:
        // - o usare @Primary su uno specifico Bean
        // - oppure usare @Qualifier per specificare quale Bean scegliere per nome (qui direttamente nel costruttore)
        // se utilizzassi entrambi, vincerebbe il @Qualifier
        // this.student = new BackendStudent("Ajeje", "Brazorf"); // <-- ACCOPPIAMENTO FORTE, NON LO VOGLIAMO!
        // Invece quello che stiamo facendo è debole, quindi flessibile e facilmente modificabile
        this.student = student;
    }

    // METODI
    public void askQuestion() {
        System.out.println("Hey " + student.getName() + " please solve this challenge!");
        student.answerQuestion();
    }
    @Override
    public String toString() {
        return "Interviewer{" +
                "student=" + student +
                '}';
    }
}
