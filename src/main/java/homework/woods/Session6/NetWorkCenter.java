package homework.woods.Session6;

public class NetWorkCenter {

	public static void main(String[] args){
		TCPClient tcpClient = new TCPClient();
		TCPServer tcpServer = new TCPServer();


		tcpClient.connect(tcpServer);

		tcpClient.disConnect(tcpServer);




	}
}
