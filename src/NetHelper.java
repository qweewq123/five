import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;

public class NetHelper {
	public static final int PORT = 8888;
	BufferedReader in;
	PrintWriter out;

	public void listen() {
		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(PORT);
					Socket s = ss.accept();
					System.out.println("server:connected");
					FiveGame.control.setStart(true);
					FiveGame.control.setChessColor(ChessModel.BLACK);
					FiveGame.control.setAllow(true);
					in = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					out = new PrintWriter(s.getOutputStream(), true);
					startReadThread();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void sendChess(int row, int col) {
		out.println("����:" + row + "," + col);
	}

	private void startReadThread() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				try {
					while (true) {
						String line = in.readLine();
						System.out.println(line);
						parseMessage(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void send(String line) {
		try {
			out.println(line);
		} catch (RuntimeException e) {
			// TODO �Զ����� catch ��
			// e.printStackTrace();
		}
	}

	protected void parseMessage(String line) {
		// TODO Auto-generated method stub
		if (line.startsWith("����")) {
			parseRowCol(line);
		} else if (line.startsWith("����")) {
			parseChat(line);
		} else if (line.startsWith("�ؿ�")) {
			FiveGame.control.resetGame();
		} else if (line.startsWith("����")) {
			rs(line);
		} else if (line.startsWith("����")) {
			doRegret(line);
			System.out.println("�������������");
		}
	}
	private void parseChat(String line) {
		String[] array = line.split("://");
		FiveGame.control.netChat(array[1]);
	}

	private void doRegret(String line) {
		// TODO �Զ����ɵķ������
		if("����".equals(line) == true){
			System.out.println("���������");
		    Object[] options ={ "ͬ��", "��ͬ��" };
		    int m = JOptionPane.showOptionDialog(null, "�Է����壬�Ƿ�ͬ�⣿", "����",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		    if (m == JOptionPane.YES_OPTION) {
		    	FiveGame.model.regretChess();
		    	send("����ͬ��");
		    	FiveGame.control.setAllow(false);
		    } else if(m == JOptionPane.NO_OPTION){
		    	send("���岻ͬ��");
	    	}
		}else if("���岻ͬ��".equals(line)){
			JOptionPane.showMessageDialog(null, "�Է���ͬ�����");
		}else if("����ͬ��".equals(line)){
			FiveGame.model.regretChess();
			FiveGame.control.setAllow(true);
			JOptionPane.showMessageDialog(null, "�Է�ͬ������Ļ�������");

		}

	}

	private void rs(String line) {
		// TODO �Զ����ɵķ������
		JOptionPane.showConfirmDialog(null, "�Է�����");
		FiveGame.model.regretChess();
	}

	private void parseRowCol(String line) {
		// TODO Auto-generated method stub
		// ����:12,13
		String temp;
		temp = line.substring(3);
		// temp 12,13
		String[] array = temp.split(",");
		// array[0] = "12"
		// array[1] = "13"
		int row = Integer.parseInt(array[0]);
		int col = Integer.parseInt(array[1]);
		FiveGame.control.putChessonline(row, col);
		// FiveGame.control.setStart(true);
	}

	public void connect(String ip) {
		try {
			Socket s = new Socket(ip, PORT);
			System.out.println("client:connected");
			FiveGame.control.setChessColor(ChessModel.WHITE);
			FiveGame.control.setStart(true);
			FiveGame.control.setAllow(false);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			startReadThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		out.println(msg);
	}

}
