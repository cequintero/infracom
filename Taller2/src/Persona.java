
public class Persona extends Thread{
	
	private int id;
	private Pasarela pasarela;
	private int direccion;

	public Persona(Pasarela pPasarela, int pId, int pDireccion) {
		id = pId;
		pasarela = pPasarela;
		direccion = pDireccion;
	}
	
	public int darId() {
		return id;
	}
	
	public int darDireccion() {
		return direccion;
	}
		
	public void run(){
		pasarela.entrar(direccion, this);
		pasarela.caminar(this);
		pasarela.salir(this);
	}
}
