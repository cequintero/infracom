import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
	public static final int PUERTO = 3400;
	public static final String SERVIDOR = "localhost";
	
	public static void main(String args[]) throws IOException {
		Socket socket = null;
		PrintWriter escritor = null;
		BufferedReader lector = null;
		
		System.out.println("Cliente ...");
		
		try{
			socket = new Socket(SERVIDOR, PUERTO);
			
			escritor = new PrintWriter(socket.getOutputStream(), true);
			lector = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e){
			System.err.println("Exception: " + e.getMessage());
			System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;
		
		System.out.println("Escriba el mensaje para enviar: ");
		fromUser = stdIn.readLine();
		escritor.println(fromUser);
		
		//si lo que llega del servidor no es null
		if ((fromServer = lector.readLine())!=null) {
		System.out.println("Respuesta del Servidor: " + fromServer);			
		} 
		
		
		escritor.close();
		lector.close();
		socket.close();
		stdIn.close();
		
	}
}
