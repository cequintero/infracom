import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class main3 {
private final static String ALGORITMO = "RSA";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
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
		PublicKey llavePublica = keyPair.getPublic();
		PrivateKey llavePrivada = keyPair.getPrivate();
		
		FileOutputStream archivo = new FileOutputStream("LlavePublica.pacm");
		ObjectOutputStream oss = new ObjectOutputStream(archivo);
		oss.writeObject(llavePublica);
		oss.close();
		
		
		archivo = new FileOutputStream("LlavePrivada.pacm");
		oss = new ObjectOutputStream(archivo);
		oss.writeObject(llavePrivada);
		oss.close();
		
		byte[] textoCifrado;
		
		long tiempoInicial = System.nanoTime();
		
		
		
		textoCifrado = Asimetrico.cifrar(llavePublica, ALGORITMO, texto);
		
		System.out.print("Texto Cifrado: ");
		imprimmir(textoCifrado);
		
		archivo = new FileOutputStream("Entrada.pacm");
		oss = new ObjectOutputStream(archivo);
		oss.writeObject(textoCifrado);
		oss.close();
		
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
