package caso1;

import java.util.ArrayList;

public class Buffer {
	
	//contenido del buffer
	private ArrayList<Mensaje> buff;
	
	//Tamaño del buffer
	private int n;
	
	public Buffer(int pTamano) {
		n = pTamano;
		buff = new ArrayList<Mensaje>();
	}
	
	public synchronized void almacenar(Mensaje m) {
		while(buff.size()==n) {
			try {
				System.out.println("Buffer lleno.. Cliente se queda esperando en el buffer");
				wait();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		buff.add(m);
		
	}
	
	public synchronized Mensaje retirar() {
		
		Mensaje m = null;
		
		if(buff.size() > 0) {
			m = buff.remove(0);
			System.out.println("Buffer no vacio... inicia procesar mensaje...");
		}
		System.out.println("Buffer tiene espacio, se intentea despertar a un cliente...");
		notify();
		return m;
	}
}
