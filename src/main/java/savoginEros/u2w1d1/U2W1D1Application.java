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


		// METODO TRADIZIONALE
		/*
		FrontEndStudent aldoStudent = new FrontEndStudent("Aldo");
		BackEndStudent giovanniStudent = new BackEndStudent("Giovanni");
		FullStackStudent giacomoStudent = new FullStackStudent("Giacomo");

		Interviewer i = new Interviewer(aldoStudent);
		 */

		// METODO NUOVO
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U2W1D1Application.class);

		String beanCheStampaUnaStringa = (String) ctx.getBean("nomeCustomBeanGetString"); // ho usato un name custom nel BeansConfig invece di usare il nome del metodo
		// getBean torna un object, quindi devo fare il casting (noi sappiamo il tipo specifico che ritorna getString() (cioè String) quindi lo castiamo)
		// mettendo tra parentesi tonde il tipo specifico
		System.out.println(beanCheStampaUnaStringa);

		String student = ctx.getBean("getFES").toString();
		System.out.println(student);

		Interviewer interviewer = (Interviewer) ctx.getBean("getInterviewer");
		// getBean torna un object, quindi devo fare il casting (noi sappiamo il tipo specifico che ritorna getInterviewer quindi lo castiamo)
		// mettendo tra parentesi tonde il tipo specifico
		System.out.println(interviewer);
		// In pratica cerca nello "scatolone" e va a cercare un bean col nome del metodo "getInterviewer"
		// Questo bean è già stato creato in precedenza nella classe di configurazione BeansConfig
		// ATTENZIONE, il configuratore risolve/assegna eventuali dipendenze collegandole in automatico,
		// mi riferisco alle dipendenze create con l'interfaccia IStudent implementata nelle 3 classi.
		// Quindi mi ritroverò ad avere a disposizione un oggetto Interviewer già creato e già con lo studente assegnato.
		// Se ho capito bene un elemento di tipo IStudent (interfaccia) si può riferire a più (in questo caso 3) classi specifiche.
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
		// qui non creerò una nuova istanza, ma di default modificherò sempre e solo quella
		// Quindi ne avremo solo una, che posso modificare in ogni momento.

		// Altro esempio pratico....
		// FullStackStudent anotherStudent = student3;  // anotherStudent è un altro riferimento a student3. SINGLETON
		// FullStackStudent anotherStudent = new FullStackStudent("Beppe"); // Creo una nuova istanza. PROTOTYPE

		ctx.close(); // sempre meglio chiudere il context



	}

}
