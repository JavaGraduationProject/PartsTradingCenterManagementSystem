package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
/**
 * ��¼����
 */
public class LingJianLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel title,lblAccountNO,lblPassword;
	private JPanel panBg,panConpanents;
	private JTextField txtAccontNo;
	private JPasswordField txtPassword;
	private JButton btnLogin,btnRegister;

	public LingJianLogin(){
		init();
	}
	public static void main(String[] args) {
		new LingJianLogin();
	}

	//��½��ע�᷽�����
	private void init(){
		this.setSize(600,400);
		this.setTitle("����������Ĺ���ϵͳ");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int sw=screenSize.width;
		int sh=screenSize.height;
		this.setLocation((sw-this.getWidth())/2,(sh-this.getHeight())/2);
		addConpants();
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	//�������
	private void addConpants() {
		this.setLayout(new GridLayout(2,1));
		this.add(this.getPanBg());
		this.add(this.getPanConpanents());

	}
	//����ͼƬ�ͱ���
	public JPanel getPanBg() {
		if(this.panBg==null){
			this.panBg=new BackgroundPanel();
			this.panBg.add(this.getTitles());
		}
		return panBg;
	}
	public JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel();

			this.panConpanents.add(this.getLblAccountNO());
			this.panConpanents.add(this.getTxtAccontNo());
			this.panConpanents.add(this.getLblPassword());
			this.panConpanents.add(this.getTxtPassword());
			this.panConpanents.add(this.getBtnLogin());
			this.panConpanents.add(this.getBtnRegister());
		}
		return panConpanents;
	}


	public JLabel getTitles(){
		if(title==null){
			title=new JLabel("����������Ĺ���ϵͳ",JLabel.CENTER);
			title.setFont(new java.awt.Font("΢���ź�",1,30));
		}
		return title;
	}
	//����Ա�˺�
	public JLabel getLblAccountNO() {
		if(lblAccountNO==null){
			lblAccountNO=new JLabel("����Ա�˺ţ� ");
		}
		return lblAccountNO;
	}
	//����Ա�˺������
	public JTextField getTxtAccontNo() {
		if(txtAccontNo==null){
			txtAccontNo=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return txtAccontNo;
	}
	//����
	public JLabel getLblPassword() {
		if(lblPassword==null){
			lblPassword=new JLabel("���룺");
		}
		return lblPassword;
	}
	//���������
	public JPasswordField getTxtPassword() {
		if(txtPassword==null){
			txtPassword=new JPasswordField(20);
			txtPassword.setEchoChar('*');
			txtPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						loginvalite();
					}
				}
			});
		}
		return txtPassword;
	}
	//ע�ᰴť
	public JButton getBtnRegister() {
		if(btnRegister==null){
			btnRegister=new JButton("ע��");
			btnRegister.setFont(new Font("����",Font.BOLD,14));
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//�رյ�ǰҳ��
					LingJianLogin.this.dispose();
					//ע�᷽������ע��ҳ��
					register();
				}
			});
		}
		return btnRegister;
	}
	public void register() {
		Register rs=new Register();
		rs.register();
	}
	//��½��ť
	public JButton getBtnLogin() {
		if(btnLogin==null){
			btnLogin=new JButton("��¼");
			btnLogin.setFont(new Font("����",Font.BOLD,14));
			btnLogin.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					loginvalite();
				}
			});
		}
		return btnLogin;
	}

	//��½��֤������
	private void loginvalite() {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ���ı����ȡ����
		String accountNo=txtAccontNo.getText().trim();
		if(accountNo==null||accountNo.equals("")){
			JOptionPane.showMessageDialog(LingJianLogin.this, "�˺Ų���Ϊ�գ�");
			txtAccontNo.requestFocus();
			return;
		}
		//��ȡ����
		String password=String.valueOf(txtPassword.getPassword());
		if(password==null||password.equals("")){
			JOptionPane.showMessageDialog(LingJianLogin.this, "���벻��Ϊ��");
			txtPassword.requestFocus();
			return;
		}
		try {
			//��ѯ����Ա����֤��½��Ϣ
			stat=conn.createStatement();		
			String sql="select * from t_trader where trader_id='"+accountNo+"' and trader_password='"+password+"'";
			rs=stat.executeQuery(sql);
			if(rs.next()){
				//�رյ�ǰҳ��
				LingJianLogin.this.dispose();
				//��ϵͳ������
				new LingJianMainPage();

			}else{
				JOptionPane.showMessageDialog(LingJianLogin.this, "�˺Ż��������");
				txtPassword.setText("");
				txtPassword.requestFocus();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(LingJianLogin.this, "�������ݿ�ʧ�ܣ�");
		}finally{
			//�ر����ݿ�������Դ
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
