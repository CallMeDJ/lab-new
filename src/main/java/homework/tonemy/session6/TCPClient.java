package homework.tonemy.session6;

public class TCPClient {

	private TCPStatus status ;

	private int ackNumber;

	public void connect(TCPServer tcpServer)   {

		//TODO 这里是入口，表示建立连接，主要逻辑都在 sendMessage 和  recieveMessage。每次状态变化都打印出来信息和状态。
		status = TCPStatus.CLOSED;
		System.out.println("Client : " + status);

		tcpServer.listen();
		sendMessage(1, ackNumber, 0, 0, 0, tcpServer);

	}

	public void disConnect(TCPServer tcpServer) {

		//TODO 这里是入口吧，表示断开连接，主要逻辑都在 sendMessage 和  recieveMessage，每次状态变化都打印出来信息和状态。
		sendMessage(0, 0, this.ackNumber, 0,1, tcpServer);

	}

	public void sendMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPServer tcpServer){
		//TODO  实现发送给 TCPServer 的握手或者挥手逻辑
		if(SYN == 1) {
			status = TCPStatus.SYN_SENT;
			System.out.println("CLient : " + status);
		}

		if(FIN == 1) {
			status = TCPStatus.FIN_WAIT_1;
			System.out.println("Client : " + status);
		}
		if(FIN == 1 && ACK == 1) {
			status = TCPStatus.TIME_WAIT;
			System.out.println("Client : " + status);
			tcpServer.recieveMessage(SYN, ACK, seq, ack, FIN, this);
		}

        tcpServer.recieveMessage(SYN, ACK, seq, ack, FIN, this);
	}

	public void recieveMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPServer tcpServer){
		//TODO  实现接收来自 TCPSerer 的握手或者挥手逻辑
		if(SYN == 1) {
            status = TCPStatus.ESTABLISHED;
            System.out.println("CLient : " + status);
            this.ackNumber = ack;
			sendMessage(0, 1, this.ackNumber, seq + 1, 0, tcpServer);
		}else if(FIN == 1) {
		    status = TCPStatus.TIME_WAIT;
		    System.out.println("Client : " + status);
		    this.ackNumber = ack;
            sendMessage(0, 1, ackNumber, seq + 1, 0, tcpServer);
            status = TCPStatus.CLOSED;
            System.out.println("Client : " + status);
        }else if(ACK == 1 && ack == this.ackNumber + 1) {
            status = TCPStatus.FIN_WAIT_2;
            System.out.println("Client : " + status);
        }


	}

}