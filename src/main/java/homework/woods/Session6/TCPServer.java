package homework.woods.Session6;

import homework.woods.utils.WoodsPrinter;

public class TCPServer {
	private TCPStatus status;

	private int ackNumber;


	public void listen(){
		// 表示服务端准备好可以接收 TCP 连接了
		WoodsPrinter.println("Server : " + TCPStatus.CLOSED);
		WoodsPrinter.println("Server : " + TCPStatus.LISTEN);
	}



	public void sendMessage(int SYN, int ACK, String seq, String ack, int FIN, TCPClient tcpClient){
		//TODO  实现发送给 TCPClient 的握手或者挥手逻辑
		TCPClient.print(SYN, ACK, seq, ack, FIN);
		if (FIN == 1){
			WoodsPrinter.println("Server : " + TCPStatus.LAST_ACK);
			tcpClient.recieveMessage(SYN, ACK, seq, ack , FIN, this);
			return;
		}
		tcpClient.recieveMessage(SYN, ACK, seq, ack, FIN, this);


	}


	public void recieveMessage(int SYN, int ACK, String seq, String ack, int FIN, TCPClient tcpClient){
		//TODO  实现接收来自 TCPClient 的握手或者挥手逻辑
		// 建立连接
		if (SYN == 1) {
			status = TCPStatus.SYS_RCVD;
			WoodsPrinter.println("Server : " + status);
			this.sendMessage(1, 1, "y", seq + "+1", 0, tcpClient);
			return;
		}

		if (FIN ==1)
		{
			status = TCPStatus.CLOSE_WAIT;
			sendMessage(0, 1, "y", seq + "+1", 0, tcpClient);
			WoodsPrinter.println("Server : " + status);
			WoodsPrinter.println("");
			WoodsPrinter.println("");

			return;
		}
		if (status.equals(TCPStatus.SYS_RCVD)) {
			status = TCPStatus.ESTABLISHED;
			WoodsPrinter.println("Server : " + status);
			return;
		}

		if (status.equals(TCPStatus.LAST_ACK))
		{
			status = TCPStatus.CLOSED;
			WoodsPrinter.println("Server : " + status);
			return;
		}



	}


}
