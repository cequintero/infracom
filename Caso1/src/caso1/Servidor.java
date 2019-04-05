package caso1;

public class Servidor extends Thread{

	private Buffer buff;
	private int id = 0; 



	public Servidor(Buffer pBuffer, int pId) {
		buff = pBuffer;
		id = pId;

	}

	public void run() {	
		int iteracion = 0; // variable usada para facilitar trazado del servidor en consola
		while(true) {
			
			Mensaje m = buff.retirar();
			while(m == null) {
				System.out.println("|"+ id +"-"+ iteracion +"|" + "Servidor [" + id + "] no encuentra mensajes en el buffer, esperando mensajes...");
				yield();
				try {
					sleep(500);
					//System.out.println("-----------------");//facilita trasado con un solo servidor
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iteracion++;
			}

			int aux = m.getConsulta();

			System.out.println("|"+ id +"-"+ iteracion +"|" + "Servidor ["+ id +"] contenido del mensaje : " +  m.getConsulta());
			aux++;
			System.out.println("|"+ id +"-"+ iteracion +"|" + "Servidor ["+ id +"] respuesta mensaje : " +  aux);
			m.notificarCliente();

			try {
				sleep(500);
				//System.out.println("-----------------");//facilita trasado con un solo servidor
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			iteracion++;
		}

	}

}
