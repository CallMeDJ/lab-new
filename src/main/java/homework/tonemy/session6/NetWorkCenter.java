package homework.tonemy.session6;


public class NetWorkCenter {

	public static void main(String[] args)   {
		TCPClient tcpClient = new TCPClient();
		TCPServer tcpServer = new TCPServer();
        //三次握手
        System.out.println("Start a connect");
		tcpClient.connect(tcpServer);
        //三次挥手
        System.out.println("End a connect");
		tcpClient.disConnect(tcpServer);


	}
}
