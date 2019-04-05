
public class MayorMatrizThread extends Thread{
	private final static int SIZE = 20;
	
	private static int[][] matriz;
	private static int mayor = 0;
	
	private int id;
	
	private int mayorFila;
	
	public MayorMatrizThread(int n) {
		id = n;
		mayor = -1;
	}
	
	public void run() {
		mayorFila = matriz[id][0];
		for (int j = 0; j < matriz[id].length; j++) {
			if(matriz[id][j] > mayorFila) {
				mayorFila = matriz[id][j];
			}
		}
		
		if (mayorFila > mayor) {
			mayor = mayorFila;
		}
	}
	
	private static void crearMatriz(int n) {
		matriz =new int[n][n];
		
		for(int i = 0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				matriz[i][j] = (int) (Math.random()*1000);				
			}
		}
	}
	
	private static void imprimirMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for(int j = 0; j< matriz[i].length; j++) {
				System.out.println(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		int i;
		
		MayorMatrizThread[] t = new MayorMatrizThread[SIZE];
		
		crearMatriz(SIZE);
		
		for ( i = 0; i < SIZE; i++) {
			t[i] =new MayorMatrizThread(i);
			t[i].start();
		}
		
		imprimirMatriz();
		
		System.out.println("\nEl mayor elemento de la matriz es: " + mayor);
	}
	
}
