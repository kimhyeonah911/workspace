package q.thread.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend extends Thread{
	private Socket socket;

	public ServerSend(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try(PrintWriter pw = new PrintWriter(socket.getOutputStream()); Scanner sc = new Scanner(System.in);) {
			while(true) {
				System.out.print("클라이언트로 보낼 내용 : ");
				System.out.println("");
				String sendMessage = sc.nextLine();
				
				pw.println(sendMessage);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}
	
	
}
