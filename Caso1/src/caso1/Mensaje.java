package caso1;

public class Mensaje {

	private int consulta;
	private int respuesta;
	
	public Mensaje(int pConsulta) {
		consulta = pConsulta;
	}
	
	public synchronized void esperarRespuesta() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getConsulta() {
		return consulta;
	}
	
	public void setRespuesta(int pRespuesta) {
		respuesta = pRespuesta;
	}
	
	public int getRespuesta() {
		return respuesta;
	}

	public synchronized void notificarCliente() {
		notify();		
	}
	
}
