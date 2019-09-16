package com.bigbanana.lab.Session6;

public class TCPClient {

	private TCPStatus status;

	private int ackNumber;



	public void connect(TCPServer tcpServer){

		//TODO 这里是入口，表示建立连接，主要逻辑都在 sendMessage 和  recieveMessage。每次状态变化都打印出来信息和状态。

	}


	public void disConnect(TCPServer tcpServer){

		//TODO 这里是入口吧，表示断开连接，主要逻辑都在 sendMessage 和  recieveMessage，每次状态变化都打印出来信息和状态。

	}

	public void sendMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPServer tcpServer){
		//TODO  实现发送给 TCPServer 的握手或者挥手逻辑


	}


	public void recieveMessage(int SYN,int ACK,int seq,int ack,int FIN,TCPServer tcpServer){
		//TODO  实现接收来自 TCPSerer 的握手或者挥手逻辑



	}


}
