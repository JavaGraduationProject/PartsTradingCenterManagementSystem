package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
/**
 * ע�����
 */
public class Register extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel acctNo,name,passWord;
	private JPanel panConpanents;
	private JTextField jtAcctNo,jtName;
	private JPasswordField jxPassWord;
	private JButton register,login;

	public Register(){
		register();
	}
	//ע�᷽��
	public void register(){
		this.setSize(500,300);
		this.setTitle("ע��ҳ��");
		addConpants();
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}

	//�������
	public void addConpants() {
		this.setLayout(new FlowLayout());//��ʽ����
		this.add(this.getPanConpanents());
	}
	private JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(4,2,10,10));
			//��һ��
			this.panConpanents.add(this.getAcctNo());
			this.panConpanents.add(this.getTxAcctNo());
			//�ڶ���
			this.panConpanents.add(this.getNames());
			this.panConpanents.add(this.getJtName());
			//������
			this.panConpanents.add(this.getPassWord());
			this.panConpanents.add(this.getJxPassWord());
			//������
			this.panConpanents.add(this.getBtnRegister());
			this.panConpanents.add(this.getBtnLogin());
		}
		return panConpanents;
	}
	//��Ӧ����Ա�˺�
	public JLabel getAcctNo(){
		if(acctNo==null){
			acctNo=new JLabel("����Ա�˺ţ�",JLabel.CENTER);
		}
		acctNo.setHorizontalAlignment(SwingConstants.CENTER);
		return acctNo;
	}
	//��Ӧ����Ա�˺������
	public JTextField getTxAcctNo(){
		if(jtAcctNo==null){
			jtAcctNo=new JTextField(16);//����һ������ָ���������µĿ� TextField
		}
		return jtAcctNo;
	}
	//��Ӧ����Ա����
	public JLabel getNames(){
		if(name==null){
			name=new JLabel("����Ա���ƣ�",JLabel.CENTER);
		}
		name.setHorizontalAlignment(SwingConstants.CENTER);
		return name;
	}
	//��Ӧ����Ա���������
	public JTextField getJtName(){
		if(jtName==null){
			jtName=new JTextField(16);//����һ������ָ���������µĿ� TextField
		}
		return jtName;
	}
	//��Ӧ����Ա����
	public JLabel getPassWord(){
		if(passWord==null){
			passWord=new JLabel("����Ա���룺",JLabel.CENTER);
		}
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		return passWord;
	}
	//��Ӧ����Ա���������
	public JPasswordField getJxPassWord(){
		if(jxPassWord==null){
			jxPassWord=new JPasswordField(16);//����һ������ָ���������µĿ� JPasswordField
		}
		return jxPassWord;
	}
	public JButton getBtnRegister(){
		if(register==null){
			register=new JButton("ע��");
			register.setFont(new Font("����", Font.BOLD, 12));
			register.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					doRegister();
				}
			});
		}
		return register;
	}
	public JButton getBtnLogin(){
		if(login==null){
			login=new JButton("��¼");
			login.setFont(new Font("����", Font.BOLD, 12));
			login.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�رյ�ǰҳ��
					Register.this.dispose();
					//�򿪵�¼ҳ��
					new LingJianLogin();
				}
			});
		}
		return login;
	}
	//ע��������
	public void doRegister() {
		//��ȡ����Ա�˺����������
		String accountNo=jtAcctNo.getText().trim();
		if(null==accountNo||"".equals(accountNo)){
			JOptionPane.showMessageDialog(Register.this, "����Ա�˺Ų���Ϊ�գ�");
			jtAcctNo.requestFocus();
			return;
		}
		//��ȡ����Ա�˺����������
		String jyName=jtName.getText().trim();
		if(null==jyName||"".equals(jyName)){
			JOptionPane.showMessageDialog(Register.this, "����Ա���Ʋ���Ϊ�գ�");
			jtName.requestFocus();
			return;
		}
		//��ȡ����
		String password=String.valueOf(jxPassWord.getPassword());
		if(password==null||password.equals("")){
			JOptionPane.showMessageDialog(Register.this, "����Ա���벻��Ϊ��");
			jxPassWord.requestFocus();
			return;
		}

		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Connection conn = null;
		Statement stat=null;
		ResultSet rs = null;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();		
			String sqlQuery="select * from t_trader where trader_id='"+accountNo+"' and trader_name='"+jyName+"'";
			//��ѯ���ݿ����Ƿ��Ѵ��ڴ˽���Ա�������״������ڿɼ���ע��
			rs=stat.executeQuery(sqlQuery);
			if(rs.next()){
				JOptionPane.showMessageDialog(Register.this, "�ý���Ա�Ѵ��ڣ�������ע��");
				jtAcctNo.setText("");
				jtAcctNo.requestFocus();
				jtName.setText("");
				jtName.requestFocus();
				jxPassWord.setText("");
				jxPassWord.requestFocus();
				return;
			}else{
				String sqlInsert="insert into t_Trader (trader_id,trader_name,trader_password) values ('"+accountNo+"','"+jyName+"','"+password+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(Register.this, "ע��ɹ������¼");
				}else{
					JOptionPane.showMessageDialog(Register.this, "ע��ʧ�ܣ�������ע��");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
