package homework.lxy.Session6;

public class TCPClient {

	private final int MSL_TIME = 100;

	private TCPStatus status;

	private String ackNumber;

	private int waitTime = 2 * MSL_TIME;


	public void connect(TCPServer tcpServer){
		tcpServer.listen();
		this.sendMessage(1, 0, "x", "", 0, tcpServer);
		status = TCPStatus.SYN_SENT;
		System.out.println(status);

	}


	public void disConnect(TCPServer tcpServer){
		status = TCPStatus.FIN_WAIT_1;
		this.sendMessage(0,0,"m","",1,tcpServer);
	}

	public void sendMessage(int SYN,int ACK,String seq,String ack,int FIN,TCPServer tcpServer){
		tcpServer.recieveMessage(SYN, ACK, seq, ack, FIN, this);
	}


	public void recieveMessage(int SYN,int ACK,String seq,String ack,int FIN,TCPServer tcpServer){
		//握手收到响应
		if(SYN == 1 && ACK == 1){
			status = TCPStatus.ESTABLISHED;
			ackNumber = seq+1;
			System.out.println(status);
			this.sendMessage(0,1,"x+1",ackNumber,0,tcpServer);
			return;
		}
		/**
		 * 	服务端已处理完所有请求包并发送结束报文，此时两种情况
		 * 	1.FIN_WAIT2状态：变更状态为TIME_WAIT 等待2MSL
		 * 	2.TIME_WAIT状态：证明last_ack报文没收到，故打算睡眠，重新发送并将等待时间置为原来的2倍
		 */
		if(FIN == 1){
			if(status.equals(TCPStatus.FIN_WAIT_2)){
				status = TCPStatus.TIME_WAIT;
				this.sendLastMsg("m+1",tcpServer);
			}
			if(status.equals(TCPStatus.TIME_WAIT)){
				Thread.currentThread().interrupt();
				this.sendLastMsg("m+1",tcpServer);
			}
			System.out.println(status);
		}

		//挥手时收到服务端"收到请求，等待处理"的响应
		if(ACK == 1){
			status = TCPStatus.FIN_WAIT_2;
			System.out.println(status);
		}




	}
	private void sendLastMsg(String seq, TCPServer tcpServer){
		try {
			this.sendMessage(0,1,"m+1",seq+"1",0,tcpServer);
			Thread.sleep(waitTime);
		}catch (InterruptedException e){
			System.out.println("message loss,will be resend");
		}
	}

}
