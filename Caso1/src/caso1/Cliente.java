package caso1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Thread {
	
	int id;
	int consultas; 
	Buffer b;
	
	public Cliente(int pConsultas, int pId, Buffer pB) {
		consultas = pConsultas;
		id = pId;
		b = pB;
	}
	
	public void run() {
		for(int i = 0; i < consultas; i++) {
			int mensaje = (int) (Math.random() * 100);
			System.out.println("Cliente " + id + " Crea el mensaje " + mensaje + "");
			
			Mensaje m = new Mensaje(mensaje);
			
			b.almacenar(m);
			System.out.println("Cliente [" + id + "] se queda esperando respuesta del servidor");
			m.esperarRespuesta();
			System.out.println("Cliente [" + id + "] notificado de respuesta del servidor");
			
			//TODO
			
			
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		int nClientes = 0;
		int nConsultas = 0;
		int nServidores = 0;
		
		File file = new File("src/doc/datosIniciales.txt");
		
		
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String a = input.readLine();
		String[] aux = a.split("=");
		nClientes = Integer.parseInt(aux[1]);
		
		a = input.readLine();
		aux = a.split("=");
		
		nConsultas = Integer.parseInt(aux[1]);
		a = input.readLine();
		
		aux = a.split("=");
		nServidores = Integer.parseInt(aux[1]);
		
		System.out.println("Numero de clientes: " + nClientes + " Numero de consultas: " + nConsultas + " Numero de servidores: " + nServidores);
		
		System.out.println("Creando buffer");
		Buffer b = new Buffer(5);
		
		for(int i=0; i < nClientes; i++) {
			System.out.println("Creando cliente [" + i +"]");
			Cliente c = new Cliente(nConsultas, i, b);
			System.out.println("Iniciando cliente [" + i + "]");
			c.start();
		}
		
		try {
			sleep(5000);
			System.out.println("---------------------------------------------------------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i < nServidores ;i++) {
			System.out.println("Creando servidor [" + i + "]");
			Servidor s = new Servidor(b, i);
			System.out.println("Iniciando servidor [" + i +"]");
			s.start();
		}
		
	}
	
}

