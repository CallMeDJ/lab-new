package homework.tonemy.session6;


public class TCPServer {
	private TCPStatus status;

	private int ackNumber;

	public void listen(){
		// 表示服务端准备好可以接收 TCP 连接了
		status = TCPStatus.CLOSED;
        System.out.println("Server : " + status);

        status = TCPStatus.LISTEN;
        System.out.println("Server : " + status);
	}

	public void sendMessage(int SYN,int ACK,int seq,int ack,int FIN, TCPClient tcpClient){
		//TODO  实现发送给 TCPClient 的握手或者挥手逻辑
        tcpClient.recieveMessage(SYN, ACK, seq, ack, FIN, this);

	}

	public void recieveMessage(int SYN,int ACK,int seq,int ack,int FIN, TCPClient tcpClient){
		//TODO  实现接收来自 TCPClient 的握手或者挥手逻辑
        if(SYN == 1) {
            status = TCPStatus.SYN_RCVD;
            System.out.println("Server : " + status);
			sendMessage(1, 1, this.ackNumber, seq + 1, 0, tcpClient);
        }
		if(ACK == 1 && ack == this.ackNumber + 1) {
			status = TCPStatus.ESTABLISHED;
			System.out.println("Server : " + status);
		}
		if(FIN == 1) {
		    this.ackNumber = ack;
		    status = TCPStatus.CLOSE_WAIT;
		    System.out.println("Server : " + status);
			sendMessage(0, 1, this.ackNumber, seq + 1, 0, tcpClient);
            status = TCPStatus.LAST_ACK;
            System.out.println("Server : " + status);
			sendMessage(0, 1, this.ackNumber + 1, seq + 1, 1, tcpClient);
			status = TCPStatus.CLOSED;
			System.out.println("Server : " + status);
        }

	}

}