public class Consumidor extends Thread {
	private Buffer buffer;
	private int mensajesPorRetirar;
	public Consumidor(int mensajes, Buffer buffer) {
		this.mensajesPorRetirar = mensajes;
		this.buffer = buffer;
	}
	public void run() {
		while (mensajesPorRetirar > 0) {
			Integer mensaje = buffer.retirar();
			System.out.println("Retirando del buffer el mensaje " + mensaje);
			mensajesPorRetirar--;
		}
	}
}
