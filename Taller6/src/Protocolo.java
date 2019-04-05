import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Protocolo 
{
	public static void procesar(BufferedReader pIn, PrintWriter pOut) throws IOException
	{
		String inputLine, outputLine;
		
		inputLine = pIn.readLine();
		System.out.println("Entrada a procesar: " + inputLine);
		
		outputLine = inputLine;
		System.out.println("Salidad procesada: " + outputLine);
		
		pOut.print(outputLine);
	}
}