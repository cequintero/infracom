public class Productor extends Thread {
	private Buffer buffer;
	private int mensajesPorAlmacenar;
	public Productor (int mensajes, Buffer buffer) {
		this.mensajesPorAlmacenar = mensajes;
		this.buffer = buffer;
	}
	public void run() {
		int m=0;
		while(mensajesPorAlmacenar>0) {
			// cada mensaje es un entero numerado desde 0.
			buffer.almacenar(m);
			mensajesPorAlmacenar--;
			m++;
		}
	}
}
