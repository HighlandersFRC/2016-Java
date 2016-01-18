package org.usfirst.frc.team4499.robot.tools;
import static com.oracle.jrockit.jfr.DataType.INTEGER;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Tegra implements Runnable{
	int x;
	int y;
	int port;
	String fromClient = "";
    String toClient = "";
    ServerSocket serverSocket;
    boolean run = true;
    Thread tegra;
	public Tegra() throws IOException{
	        port = 9009;
	        
	        this.start();
	    }

	    public static int[] parsePoint(String line) {
	        int point[] = {0,0};
	        int position = 0;
	        while(line.charAt(position) != '('){
	            position++;
	        }
	        position++;
	        while (line.charAt(position) != ',') {
	            point[0] = (point[0] * 10) + (int)line.charAt(position) - 48;
	            System.out.println(point[0]);
	            position++;
	        }
	        position++;
	        while (line.charAt(position) != ')') {
	            point[1] = (point[1] * 10) + (int)line.charAt(position) - 48;
	            position++;
	        }

	        return point;

	    }

		@Override
		public void run() {
			
			try{
				 ServerSocket serverSocket = new ServerSocket(port);
				 while (run) {	
			            Socket socket = serverSocket.accept();
			            System.out.println("Got Connection from: " + socket.getLocalAddress());
			            while (run) {
			                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			                fromClient = in.readLine();
			                int point[] = parsePoint(fromClient);
			                // Scanner scanner = new Scanner(fromClient);
			                //int x = scanner.nextInt();
			                // int y = scanner.nextInt();
			                System.out.println("received: "  +"(" +(point[0] + 100) +","+ (point[1] + 100) + ")"); 
			                x = point[0];
			                y = point[1];
			            }

			        }
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		public void start(){
			System.out.println("Creating Tegra thread");
			tegra = new Thread(this,"TegraData");
			System.out.println("Starting Tegra thread");
			tegra.start();
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
	}



