public class Main {
	public static void main(String[] args) {
		Buffer buffer = new Buffer(5);
		int mensajeParaRetirar = 20;
		int mensajesParaAlmacenar = 20;
		Productor p = new Productor(mensajesParaAlmacenar, buffer);
		Consumidor c = new Consumidor(mensajeParaRetirar, buffer);
		p.start();
		c.start();
	}
}