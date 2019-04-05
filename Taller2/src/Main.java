import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pasarela pasa = new Pasarela();
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
			int direccion = random.nextInt(2);
			Persona p = new Persona(pasa, i, direccion);
			p.start();
		}

		

	}

}
