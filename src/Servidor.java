import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Enumeration;

public class Servidor {

	public static void main(String[] args) {
		
		
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("");
			System.out.println("Esperando conexión...");
			System.out.println("");
			Socket socket = server.accept();
			System.out.println("CONECTADO");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
				
				
			while(true){
				
				String line = bReader.readLine();	
				System.out.println("Usuario escribio: "+line);
				InetAddress address = InetAddress.getLocalHost();
				
				String answer = "";
				
				
				switch(line){
					case "ip":
						answer = "La direccion IP del servidor es: "+address.getHostAddress()+" \n";
						bWriter.write(answer);
						bWriter.flush();
						break;
						
					case "interface":
						Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
				        while (interfaces.hasMoreElements()) {
				            NetworkInterface iface = interfaces.nextElement();
				            
				            if (iface.isUp())
				            	answer+= iface.getDisplayName()+" \n";
				        }
				        
				        bWriter.write(answer);
						bWriter.flush();
						break;
						
						
					case "hora":
						Calendar cal = Calendar.getInstance();
						answer = "La hora del servidor es: "+cal.getTime().toString()+" \n";
						
						bWriter.write(answer);
						bWriter.flush();
						break;
						

					default:
						answer = line;
						
						bWriter.write(answer);
						bWriter.flush();
						break;

				}
				
				
					
				}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
