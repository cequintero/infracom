import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.TreeSet;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;

public class Main {
	
	private final static String ALGORITMO = "SHA1withRSA";
	
	private static String printHexBinary(byte[] certificadoEnBytes) {
		String string = new String(certificadoEnBytes);

		string = new String(DatatypeConverter.printHexBinary(certificadoEnBytes));

		return string;
		
	}
	
	private static X509Certificate generarCertificado(KeyPair  keyPair) throws CertificateException, OperatorCreationException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		AlgorithmIdentifier sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(ALGORITMO);
        AlgorithmIdentifier digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);
        AsymmetricKeyParameter privateKeyAsymKeyParam = PrivateKeyFactory.createKey(keyPair.getPrivate().getEncoded());
        
		org.bouncycastle.asn1.x500.X500Name emisor = new X500Name("CN=Test, L=London, C=GB");
		BigInteger serial = new BigInteger(64, new SecureRandom());
		Date notBefore = new Date(2019, 01, 01);
		System.out.println("Generado certificado: fecha de emicion: " + notBefore.toString());
		Date notAfter = new Date(2020, 01 ,01);

		SubjectPublicKeyInfo subPubKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());
		
		System.out.println("Generado certificado: fecha de emicion: " + notAfter .toString());
		X509v3CertificateBuilder b = new X509v3CertificateBuilder(emisor, serial, notBefore, notAfter, emisor, subPubKeyInfo);
		
		ContentSigner sigGen = new BcRSAContentSignerBuilder(sigAlgId, digAlgId).build(privateKeyAsymKeyParam);
		X509CertificateHolder certificateHolder = b.build(sigGen);
		
		return new JcaX509CertificateConverter().setProvider("BC").getCertificate(certificateHolder);
	}
	
	
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, CertificateException, OperatorCreationException {
		TreeSet<String> algorithms = new TreeSet<>();
		for (Provider provider : Security.getProviders())
		    for (Service service : provider.getServices())
		        if (service.getType().equals("Signature"))
		            algorithms.add(service.getAlgorithm());
		for (String algorithm : algorithms)
		    System.out.println(algorithm);
		
		
		String servidorNombre = "localhost";
		int puerto = 8080;             //puerto
		PrintWriter out = null;
		BufferedReader in = null;
		Socket server = null;

		try {
			// Se abre un socket conectado al servidor y al
			server = new Socket( servidorNombre, puerto );
			System.out.println( "Socket Abierto." );

			// Se consigue el canal de entrada
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader( server.getInputStream()));

			//INICIA PROTOCOLO DE COMUNICACION
			out.println("HOLA"); // se envia mensaje "HOLA" al servidor
			System.out.println("Envia mensaje de apertura, espera respuesta del servidor");

			System.out.println("Server: " + in.readLine()); // Espera respuesta por parte del servidor
			
			System.out.println("Envia mensaje con los algoritmos que implementara, espera respuesta del servidor");
			out.println("ALGORITMOS:AES:"+"RSA"+":HMACSHA1"); // se envian los protocolos

			System.out.println("Server: " + in.readLine()); // Espera respuesta por parte del servidor
			
			//Inicia intercambio de certificados
			
			
			//Generacion de claves
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair keyPair = generator.generateKeyPair();
			
			java.security.cert.X509Certificate certificado = generarCertificado(keyPair);
			byte[] certificadoEnBytes = certificado.getEncoded( );
			
			String certificadoEnString = printHexBinary(certificadoEnBytes);
			out.println(certificadoEnString);
			
			System.out.println("Server: " + in.readLine()); // Espera respuesta por parte del servidor
			
			BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
			String userInput;

			while ((userInput = stdIn.readLine()) != null) {
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}

		} catch( UnknownHostException e ) {
			System.out.println( e );
			System.exit(1);
		} catch( IOException e ) {
			System.out.println( e );
			System.exit(1);
		}finally {
			out.close();
			in.close();
			server.close();
		}


	}

	

	
}

