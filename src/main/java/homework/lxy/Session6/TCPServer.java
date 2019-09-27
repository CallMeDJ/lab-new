package homework.lxy.Session6;

public class TCPServer {
	private TCPStatus status;

	private String ackNumber;


	public void listen(){
		status = TCPStatus.LISTEN;
		System.out.println(status);
		// 表示服务端准备好可以接收 TCP 连接了
	}



	public void sendMessage(int SYN,int ACK,String seq,String ack,int FIN,TCPClient tcpClient){
		//TODO  实现发送给 TCPClient 的握手或者挥手逻辑
		tcpClient.recieveMessage(SYN,ACK,seq,ack,FIN,this);

	}


	public void recieveMessage(int SYN,int ACK,String seq,String ack,int FIN,TCPClient tcpClient){
		//第一次收到客户端招手
		if(SYN == 1 && status.equals(TCPStatus.LISTEN)){
			status = TCPStatus.SYN_RCVD;
			System.out.println(status);
			this.sendMessage(1,1,seq+"1","y",0,tcpClient);
		}

		//收到客户端挥手，先相应，处理完剩余请求包后再结束
		if(FIN == 1){
			status = TCPStatus.CLOSE_WAIT;
			System.out.println(status);
			ackNumber = seq+"1";
			this.sendMessage(0,1,"n",ackNumber,0,tcpClient);
			try {
				Thread.sleep(200);
				this.sendMessage(0,1,"o",ackNumber,1,tcpClient);
				status = TCPStatus.LAST_ACK;
			}catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		//两种情况，客户端最后一次招手和最后一次挥手
		if(ACK == 1){
			if(status.equals(TCPStatus.SYN_RCVD)){
				status = TCPStatus.ESTABLISHED;
			}
			if(status.equals(TCPStatus.LAST_ACK)){
				status = TCPStatus.CLOSED;
			}
			System.out.println(status);
		}

	}


}
