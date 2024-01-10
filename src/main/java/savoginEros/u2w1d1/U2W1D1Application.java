package savoginEros.u2w1d1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import savoginEros.u2w1d1.entities.BackEndStudent;
import savoginEros.u2w1d1.entities.FrontEndStudent;
import savoginEros.u2w1d1.entities.FullStackStudent;
import savoginEros.u2w1d1.entities.Interviewer;

@SpringBootApplication
public class U2W1D1Application {

	public static void main(String[] args) {
		SpringApplication.run(U2W1D1Application.class, args);

		components();


	}
	public void beansConfig() {
		// ho messo tutto il codice dei beans del giorno prima qua dentro (metodo 2)

		// METODO NUOVO
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U2W1D1Application.class);


		FrontEndStudent student = (FrontEndStudent) ctx.getBean("getFES");
		System.out.println(student);

		Interviewer interviewer = (Interviewer) ctx.getBean("getInterviewer");

		System.out.println(interviewer);

		interviewer.askQuestion();

		// Stampiamo gli altri due oggetti, creandoli usando i metodi beans
		System.out.println(ctx.getBean("getBES")); // secondo bean
		// l'ultimo lo inserisco prima in una variabile come prima
		FullStackStudent student3 = (FullStackStudent) ctx.getBean("getFSS");
		System.out.println(student3);
		student3.setName("Patrizio");
		// Se modifico l'oggetto sto modificando il bean stesso,
		// di default i bean sono con scope SINGLETON, quindi nello "scatolone" ci sarà sempre una e una sola copia
		// di tale oggetto. Non è che ogni volta che faccio ctx.getBean() mi verrà creato un nuovo oggetto
		System.out.println(student3);

		System.out.println(ctx.getBean("getFSS"));

		ctx.close();
	}
	public static void components() {
		// metodo 3
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U2W1D1Application.class);

		//Interviewer i = ctx.getBean(Interviewer.class);
		// mi troverà il bean getInterviewer() (perché utilizza proprio quella classe li) e il Component Interviewer, quindi c'è un conflitto.
		// Il conflitto deriva dall'avere nel contenitore due bean uguali (che creano due istanze uguali),
		// uno presente nella BeansConfig e l'altro presente attraverso il @Component della classe Interviewer.

		// stiamo cercando per classe di tipo Interviewer ma ATTENZIONE, così mi ritroverà la classe Component Interviewer che
		// creerà un bean, poi cercherà anche tutti i bean di tipo Interviewer e ne troverà uno: getInterviewer()
		// Cerchiamo di essere più specifici assegnando un nome al Component e cercare per nome del bean nel getBean() per evitare conflitti!
		Interviewer i = (Interviewer) ctx.getBean("interviewer_component");
		// In questo modo non ci sono ambiguità, mi cercherà il bean di nome interviewer_component, che è il nome del Component
		// CURIOSITA': il nome del bean creato con Component se non dichiarato sarà il nome della classe in MINUSCOLO.
		System.out.println(i);
		i.askQuestion();
		// ho a disposizione tutti i metodi di Interviewer, quindi ad askQuestion(), che a sua volta accede
		// all'oggetto studente con @Primary, che ricordiamo che se non ci fosse ci sarebbe il conflitto con 3 bean studente che implementano tale interfaccia
		// Quindi ecco spiegato il 3° modo di creare bean, e come il secondo metodo con la classe di configurazione dei bean ho a disposizione i suoi metodi

		ctx.close();




	}


}


