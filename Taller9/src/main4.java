import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class main4 {
private final static String ALGORITMO = "RSA";
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner reader = new Scanner(System.in);
		System.out.println("Introduce un mensage");
		String texto = reader.nextLine();
		
		System.out.println("Texto: " + texto);
		
		byte[] textoClaro = texto.getBytes();
		
		System.out.print("Texto :");
		
		imprimmir(textoClaro);
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
		generator.initialize(1024);
		KeyPair keyPair = generator.generateKeyPair();
//		PublicKey llavePublica = keyPair.getPublic();
//		PrivateKey llavePrivada = keyPair.getPrivate();
		
		
		
		byte[] textoCifrado;
		
		long tiempoInicial = System.nanoTime();
		
		
		
		textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, texto);
		
		System.out.print("Texto Cifrado: ");
		imprimmir(textoCifrado);
		
		byte[] textoDescifrado = Asimetrico.descifrar(llavePrivada, ALGORITMO, textoCifrado) ;
		System.out.print("Texto Descifrado: ");
		
		imprimmir(textoDescifrado);
		
		texto = new String(textoDescifrado);
		
		System.out.println("Texto Descifrado: " + texto);
		
		long tiempoFinal = System.nanoTime();
		long tiempo = tiempoFinal - tiempoInicial;
		
		System.out.println("Tiempo ejecucion: " + tiempo);
		
		
	}
	
	
	public static void imprimmir (byte[] contenido) {
		int i = 0;
		for (; i < contenido.length - 1; i++) {
			System.out.print( contenido[i] + " "); 
		}
		System.out.println(contenido[i] + " ");
	}
}
