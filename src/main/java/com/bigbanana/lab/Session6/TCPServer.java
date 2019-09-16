package com.bigbanana.lab.Session6;

public class TCPServer {
	private TCPStatus status;

	private int ackNumber;


	public void listen(){
		// 表示服务端准备好可以接收 TCP 连接了
	}



	public void sendMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPClient tcpClient){
		//TODO  实现发送给 TCPClient 的握手或者挥手逻辑


	}


	public void recieveMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPClient tcpClient){
		//TODO  实现接收来自 TCPClient 的握手或者挥手逻辑


	}


}
