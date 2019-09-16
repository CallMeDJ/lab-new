package homework.woods.Session6;

import homework.woods.utils.WoodsPrinter;

public class TCPClient {

	private TCPStatus status;

	private int ackNumber;



	public void connect(TCPServer tcpServer){
		//TODO 这里是入口，表示建立连接，主要逻辑都在 sendMessage 和  recieveMessage。每次状态变化都打印出来信息和状态。
		status = TCPStatus.CLOSED;
		WoodsPrinter.println("Client : " + status);
		tcpServer.listen();
		sendMessage(1, 0, "x", "", 0, tcpServer);
	}


	public void disConnect(TCPServer tcpServer){
		//TODO 这里是入口吧，表示断开连接，主要逻辑都在 sendMessage 和  recieveMessage，每次状态变化都打印出来信息和状态。
		status = TCPStatus.ESTABLISHED;
		WoodsPrinter.println("Client : " + status);
		WoodsPrinter.println("Server : " + TCPStatus.ESTABLISHED);

		sendMessage(0, 0, "x", "", 1, tcpServer);
		tcpServer.sendMessage(0, 1, "z", "x+1", 1, this);


	}

	public void sendMessage(int SYN, int ACK, String seq, String ack, int FIN, TCPServer tcpServer){
		TCPClient.print(SYN, ACK, seq, ack, FIN);
		//TODO  实现发送给 TCPServer 的握手或者挥手逻辑
		if (SYN == 1) {
			status = TCPStatus.SYS_SENT;
			WoodsPrinter.println("Client : " + status);
		}
		if (FIN == 1)
		{
			status = TCPStatus.FIN_WAIT_1;
			WoodsPrinter.println("Client : " + status);
		}
		tcpServer.recieveMessage(SYN, ACK, seq, ack, FIN, this);



	}


	public void recieveMessage(int SYN, int ACK, String seq, String ack, int FIN, TCPServer tcpServer) {
		//TODO  实现接收来自 TCPSerer 的握手或者挥手逻辑
		if (SYN == 1) {
			status = TCPStatus.ESTABLISHED;
			WoodsPrinter.println("Client : " + status);
			sendMessage(0, 1, ack, seq + "+1", 0, tcpServer);
			return;
		}

		if (FIN == 1)
		{
			status = TCPStatus.TIME_WAIT;
			sendMessage(0, 1, ack + "+1", seq, 0, tcpServer);
			WoodsPrinter.println("Client : " + status);

			WoodsPrinter.println("Client : after 2MSL");
			status = TCPStatus.CLOSED;
			WoodsPrinter.println("Client : " + status);
			WoodsPrinter.println("Server : " + TCPStatus.CLOSED);
			return;
		}

		WoodsPrinter.println("Client : " + TCPStatus.FIN_WAIT_2);

	}

	public static void print(int SYN, int ACK, String seq, String ack, int FIN)
	{
		StringBuffer sb = new StringBuffer("param : ");
		if (SYN == 1)
		{
			sb.append("SYN=1,");
		}
		if (FIN == 1)
		{
			sb.append("FIN=1,");
		}
		if (ACK ==1)
		{
			sb.append("ACK=1,");
		}
		if (seq != null && !seq.isEmpty())
		{
			sb.append("seq=" + seq + ",");
		}
		if (ack != null && !ack.isEmpty())
		{
			sb.append("ack=" + ack + ",");
		}

		String pp = sb.toString();
		if (pp.lastIndexOf(",")==pp.length()-1)
		{
			pp = pp.substring(0, pp.length()-1);
		}
		WoodsPrinter.println(pp);

	}


}
