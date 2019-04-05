
public class Pasarela {

	private int pCaminandodi = 0;
	private int pCaminandoid = 0;
	
	public synchronized void entrar(int direccion, Persona p) {
		if(direccion == 1 ) {
			while (direccion == 1 && pCaminandodi>0) {
				
					try {
						System.out.println("persona "  + p.darId() + " ||| INTENTO ingresar por la : " + direccion);
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			System.out.println("persona "  + p.darId() + " ||| INGRESO por la : " + direccion);
			pCaminandoid++;
		} else {
			while (direccion == 0 && pCaminandoid>0){
				
					try {
						System.out.println("persona "  + p.darId() + " ||| INTENTO ingresar por la : " + direccion);
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			System.out.println("persona "  + p.darId() + " ||| INGRESO por la : " + direccion);
		pCaminandodi++;
		}
	}
	
	
	public void caminar(Persona p) {
		System.out.println("persona "  + p.darId() +  " ENTRA a caminar: por la : " + p.darDireccion() );
		try {
			p.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void salir(Persona p){
		if(p.darDireccion()==1) {
			System.out.println("persona "  + p.darId() + " SALE de entrar por la : " + p.darDireccion());
			pCaminandoid--;
			notifyAll();
		}else {
			pCaminandodi--;
			notifyAll();
		}
	}
	
	
}
