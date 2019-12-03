package ch.usi.pulfer.nanny_search;

import ch.usi.pulfer.nanny_search.models.InformationRetrievalSystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NannySearchApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(NannySearchApplication.class, args);
		initializeIRSystem();
	}


	public static void initializeIRSystem(){
		new Thread(new Runnable() {
			@Override
			public void run() {
					InformationRetrievalSystem.initialize();
				}
		}).start();
	}
}
