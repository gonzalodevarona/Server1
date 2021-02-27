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
			System.out.println("Esperando conexion...");
			System.out.println("");
			Socket socket = server.accept();
			System.out.println("CONECTADO");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
				
				
			while(true){
				String line = bReader.readLine();
				int test =0;
				if (line.getBytes().length < 1000) {
					
					line = line.trim();
					test = Integer.parseInt(line);
				}
				
				
				InetAddress address = InetAddress.getLocalHost();
				
				String answer = "";
				
				
				switch(test){
					case 1:
						answer = "La direccion IP del servidor es: "+address.getHostAddress()+" \n";
						
						System.out.println("Usuario consulto: IP del servidor");
						break;
						
					case 2:
						Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
				        while (interfaces.hasMoreElements()) {
				            NetworkInterface iface = interfaces.nextElement();
				            
				            if (iface.isUp())
				            	answer+= iface.getDisplayName()+" \n";
				        }
				        
				        
						System.out.println("Usuario consulto: Interfaces online del servidor");
						break;
						
						
					case 3:
						Calendar cal = Calendar.getInstance();
						answer = "La hora del servidor es: "+cal.getTime().toString()+" \n";
						
						
						System.out.println("Usuario consulto: Hora local del servidor");
						break;
						

					default:
						answer =line +" \n";
						
						
						break;

				}
				bWriter.write(answer);
				bWriter.flush();
				
				
					
				}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
