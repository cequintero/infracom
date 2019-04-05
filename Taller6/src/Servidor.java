import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static final int PUERTO = 3400;
	
	public static void main(String args[]) throws IOException{
		ServerSocket ss = null;
		boolean continuar = true;
		
		System.out.println("Main Server ...");
		
		try {
			ss = new ServerSocket(PUERTO);
		} catch (IOException e){
			System.err.println("No se pudo crear el socket en el puerto: " + PUERTO);
			System.exit(-1);
		}
		
		while (continuar){
			//crear el socket
			Socket socket =ss.accept();
			
			try{
				PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader lector = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				Protocolo.procesar(lector, escritor);
				
				escritor.close();
				lector.close();
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		ss.close();
	}
}
