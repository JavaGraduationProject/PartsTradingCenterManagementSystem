package com.lingjian;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * ���׹���
 */
public class TransactionInfo extends JFrame{


	private static final long serialVersionUID = 1L;
	private JPanel panConpanents;
	private JLabel customerNo,spareId;
	private JTextField jtCustomerNo,jtSpareId;
	private JButton jb;
	
	
	public void trans(){
		this.setSize(500,300);
		this.setTitle("������Ϣά��");
		addConpants();
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	public void addConpants() {
		this.setLayout(new FlowLayout());//��ʽ����
		this.add(this.getPanConpanents());
	}
	private JPanel getPanConpanents() {
		if(this.panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(3,2,10,10));
			this.panConpanents.add(this.getCustomerNo());
			this.panConpanents.add(this.getTxcustomerNo());
			this.panConpanents.add(this.getSpareId());
			this.panConpanents.add(this.getJtSpareId());
			this.panConpanents.add(this.getBtnSubmit());
		}
		return panConpanents;
	}

	public JLabel getCustomerNo(){
		if(customerNo==null){
			customerNo=new JLabel("�˿ͺţ�");
		}
		return customerNo;
	}
	//��Ӧ�˿ͺź������
	public JTextField getTxcustomerNo(){
		if(jtCustomerNo==null){
			jtCustomerNo=new JTextField(16);//����һ������ָ���������µĿ� TextField
		}
		return jtCustomerNo;
	}
	public JLabel getSpareId(){
		if(spareId==null){
			spareId=new JLabel("����ţ�");
		}
		return spareId;
	}
	//��Ӧ����Ա�˺������
	public JTextField getJtSpareId(){
		if(jtSpareId==null){
			jtSpareId=new JTextField(16);//����һ������ָ���������µĿ� TextField
		}
		return jtSpareId;
	}
	public JButton getBtnSubmit(){
		if(jb==null){
			jb=new JButton("ȷ��");
			jb.setFont(new Font("����", Font.BOLD, 12));
			jb.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					doTrans();
				}
			});
		}
		return jb;
	}

	public void doTrans(){
		String customerIdTxt=jtCustomerNo.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		if(null==customerIdTxt||"".equals(customerIdTxt)) {
			JOptionPane.showMessageDialog(TransactionInfo.this, "�˿��˺Ų���Ϊ�գ�");
			jtCustomerNo.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(TransactionInfo.this, "����Ų���Ϊ�գ�");
			jtSpareId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);
			stat=conn.createStatement();
			stat1=conn.createStatement();
			stat2=conn.createStatement();
			String sql02="select * from t_spare where spare_id='"+spareIdTxt+"'";
			rs=stat.executeQuery(sql02);
			if(rs.next()){
				System.out.println(rs.getString(1));
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "�������Ϣ�����ڣ�����������");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs.last();
			String sql01="select * from t_customer where customer_id='"+customerIdTxt+"'";
			rs1=stat1.executeQuery(sql01);
			if(rs1.next()){
				System.out.println(rs1.getString(1));
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "�ù˿���Ϣ�����ڣ�����������");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs1.last();
			
			String sql03="select * from t_cus_spare where customer_id='"+customerIdTxt+"' and spare_id='"+spareIdTxt+"'";
			rs2=stat2.executeQuery(sql03);
			if(rs2.next()){
				JOptionPane.showMessageDialog(TransactionInfo.this, "�ý����Ѵ��ڣ�����������");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}
			rs2.last();
			
			String sqlInsert="insert into t_cus_spare (customer_id,spare_id,number) values ('"+customerIdTxt+"','"+spareIdTxt+"',0)";
			int ret=stat.executeUpdate(sqlInsert);
			if(ret==1){
				JOptionPane.showMessageDialog(TransactionInfo.this, "�����ɹ�");
				TransactionInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(TransactionInfo.this, "����ʧ��");
				jtSpareId.setText("");
				jtCustomerNo.setText("");
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر����ݿ�������Դ
			if (rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs1!= null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs2!= null) {
				try {
					rs2.close();
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
			if (stat1 != null) {
				try {
					stat1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat2 != null) {
				try {
					stat2.close();
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
