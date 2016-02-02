package lang;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class CallBackDemo {

	public static void main(String[] args) {
		Server server = new Server();
		Client client = new Client(server);
		client.sendMsg("Hello World");
	}

}

interface CallBack {
	void process(String status);
}

class Client implements CallBack {

	private Server	server;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public Client(Server server) {
		this.server = server;
	}

	@Override
	public void process(String status) {
		System.out.println("客户端：服务端回调状态为：" + status);
	}

	public void sendMsg(final String msg) {
		System.out.println("客户端：发送的消息为：" + msg);
		new Thread(new Runnable() {
			@Override
			public void run() {
				server.getClientMsg(Client.this, msg);
			}
		}).start();
		System.out.println("客户端：异步发送成功");
	}

}

class Server {
	public void getClientMsg(CallBack callBack, String msg) {
		System.out.println("服务端：服务端接收到客户端发送的消息为:" + msg);

		// 模拟服务端需要对数据处理
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("服务端:数据处理成功，返回成功状态 200");
		String status = "200";
		callBack.process(status);
	}
}
