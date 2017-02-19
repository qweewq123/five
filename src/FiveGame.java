import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by Administrator on 2015/5/19.
 */
public class FiveGame extends JFrame {
	public static final ChessPanel view = new ChessPanel();
	public static final ChessModel model = new ChessModel();
	public static final ChessControllor control = new ChessControllor();
	public static final NetHelper helper = new NetHelper();
	public static JTextArea chatTextArea = new JTextArea();

	private JPanel northPanel = null;

	private JPanel southPanel = null;

	private JPanel westPanel = null;

	private JPanel eastPanel = null;

	private JPanel centerPanel = null;

	private JButton listenBtn = null;

	private JButton connectBtn = null;

	private JButton resetBtn = null;

	private JButton regressButton = null;

	private JButton rsButton = null;

	private JScrollPane jScrollPane = null;

	private JPanel jPanel = null;

	private JTextField messageTextField = null;

	private JButton sendButton = null;
	
	private JButton aboutButton=null;
	
	private JPanel getNorthPanel() {
		if (northPanel == null) {
			northPanel = new JPanel();
			northPanel.setOpaque(false);
			northPanel.setLayout(new FlowLayout());
			listenBtn = new JButton("����");
			listenBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					listenBtn.setEnabled(false);
					System.out.println("��ʼ��������");
					FiveGame.helper.listen();

				}
			});
			northPanel.add(listenBtn);

			connectBtn = new JButton("����");
			connectBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String ip = JOptionPane.showInputDialog("����Է�IP��ַ");
					if (ip != null) {
						FiveGame.helper.connect(ip);
					} else {
						System.out.println("IPΪ��");
					}
				}
			});
			northPanel.add(connectBtn);

			resetBtn = new JButton("�ؿ�");
			resetBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					FiveGame.helper.send("�ؿ�");
					FiveGame.control.resetGame();

				}
			});
			northPanel.add(resetBtn);
		}
		return northPanel;
	}

	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setOpaque(false);
			southPanel.setLayout(new FlowLayout());
			regressButton = new JButton("����");
			regressButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					FiveGame.helper.send("����");
//					FiveGame.model.regretChess();
				}
			});
			southPanel.add(regressButton);

			rsButton = new JButton("����");
			rsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					FiveGame.helper.send("����");
					FiveGame.model.rschess();
				}
			});
			southPanel.add(rsButton);
			
			aboutButton=new JButton("����");
			aboutButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					JOptionPane.showMessageDialog(null, "�ó�����13�����13990260��ɭ������ɣ���Ȩ�ؾ���");
				}
			});
			southPanel.add(aboutButton);

			southPanel.setOpaque(false);
		}
		return southPanel;
	}

	/**
	 * This method initializes westPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWestPanel() {
		if (westPanel == null) {
			westPanel = new JPanel();
			westPanel.setLayout(new GridBagLayout());
			westPanel.setOpaque(false);
		}
		return westPanel;
	}

	/**
	 * This method initializes eastPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new BorderLayout());
			eastPanel.add(getJScrollPane(), BorderLayout.CENTER);
			eastPanel.add(getJPanel(), BorderLayout.SOUTH);
			eastPanel.setOpaque(false);
		}
		return eastPanel;
	}


	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = FiveGame.view;
			centerPanel.setLayout(new GridBagLayout());
			centerPanel.setSize(570, 570);
			centerPanel.setOpaque(false);
		}
		return centerPanel;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setOpaque(false);
			jScrollPane.getViewport().setOpaque(false);
			jScrollPane.setViewportView(getChatTextArea());

		}
		return jScrollPane;
	}


	private JTextArea getChatTextArea() {
		if (chatTextArea == null) {
			chatTextArea.setOpaque(false);
			chatTextArea.setEnabled(false);
		}
		return chatTextArea;
	}


	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.setOpaque(false);
			jPanel.add(getMessageTextField(), BorderLayout.CENTER);
			jPanel.add(getSendButton(), BorderLayout.EAST);
		}
		return jPanel;
	}


	private JTextField getMessageTextField() {
		if (messageTextField == null) {
			messageTextField = new JTextField();
			messageTextField.setOpaque(false);
			messageTextField.setPreferredSize(new Dimension(120, 22));
		}
		return messageTextField;
	}


	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setText("����");
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FiveGame.control.sendChatMessage(messageTextField.getText());
					messageTextField.setText("");
				}
			});
		}
		return sendButton;
	}

	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		new FiveGame();

	}

	/**
	 * This is the default constructor
	 */
	public FiveGame() {
		// TODO �Զ����ɵĹ��캯�����
		// super();
		JFrame frame = new JFrame();
		Container contentPanel = frame.getContentPane();
		contentPanel.add(getNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(getSouthPanel(), BorderLayout.SOUTH);
		contentPanel.add(getWestPanel(), BorderLayout.WEST);
		contentPanel.add(getEastPanel(), BorderLayout.EAST);
		contentPanel.add(getCenterPanel(), BorderLayout.CENTER);
		frame.setSize(1000, 700);
		// frame.setBackground(Color.blue);ֱ�����ñ����Ḳ������
		frame.getContentPane().setBackground(new Color(176,224, 230));
		frame.getContentPane().setVisible(true);// �����Ϊtrue��ô�ͱ���˺�ɫ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("13990260��ɭJAVA������");
		frame.setVisible(true);
	}
}
