package com.lingjian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

/**
 *�˿���Ϣ����
 */
public class CustomerInfo extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JButton jb;
	private JPanel panConpanents,deleteCustomer;
	private JLabel customerId,customerName,customerAddr,customerPhone;
	private JTextField jtCustomerId,jtCustomerName,jtCustomerAddr,jtCustomerPhone;
	@SuppressWarnings("rawtypes")
	public  Vector rowData, columnName; 
	/**
	 * ��ѯ�˿���Ϣ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryCustomer(){
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		rowData = new Vector();
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();

			String sql="select * from t_customer";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			columnName.add("�˿��˺�");
			columnName.add("�˿�����");
			columnName.add("�˿���ϵ�绰");
			columnName.add("�˿͵�ַ");

			while (rs.next()) {
				Vector line1 = new Vector();
				for (int k = 1; k <= data.getColumnCount(); k++) {
					line1.add(rs.getString(data.getColumnName(k)));//���������������
				}
				rowData.add(line1);
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
		jt = new JTable(rowData, columnName);  
		jsp = new JScrollPane(jt);  

		this.add(jsp);  
		this.setTitle("�˿���Ϣ��ѯ");  
		this.setSize(600, 400);  //���Ǵ�С
		this.setLocation(300, 200);  //����λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setResizable(true);  //�ߴ��Ƿ�ɱ�
		this.setVisible(true);  //��ʾ������
		//ȡ��JFrame�Ĺر�Ĭ�ϲ���,Ĭ�Ϲر�ȡ���������ص�ǰ���壬ȡ�����Լ�����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer=JOptionPane.showConfirmDialog(CustomerInfo.this, "ȷ�Ϲرգ�");
				if(answer==JOptionPane.OK_OPTION){
					CustomerInfo.this.dispose();
				}
			}
		});
	}
	/**
	 * �˿���Ϣ����
	 */
	public void insertCustomer(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("�˿���Ϣ����");
		addConpants(flag);
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	public void updateCustomer(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("�˿���Ϣ����");
		addConpants(flag);
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	public void addConpants(String flag) {
		this.setLayout(new FlowLayout());//��ʽ����
		this.add(this.getPanConpanents(flag));
	}

	public JPanel getPanConpanents(String flag) {
		if(panConpanents==null){
			this.panConpanents=new JPanel(new GridLayout(6,2,10,10));
			this.panConpanents.add(this.getCustomerId());
			this.panConpanents.add(this.getTxCustomerId());
			this.panConpanents.add(this.getCustomerName());
			this.panConpanents.add(this.getJtCustomerName());
			this.panConpanents.add(this.getCustomerAddr());
			this.panConpanents.add(this.getJtCustomerAddr());
			this.panConpanents.add(this.getCustomerPhone());
			this.panConpanents.add(this.getJtCustomerPhone());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	private JLabel getCustomerId() {
		if(customerId==null){
			this.customerId=new JLabel("�˿��˺�:");
		}
		return customerId;
	}
	private JTextField getTxCustomerId() {
		if(jtCustomerId==null){
			jtCustomerId=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtCustomerId;
	}
	private JLabel getCustomerName() {
		if(customerName==null){
			this.customerName=new JLabel("�˿�����:");
		}
		return customerName;
	}
	private JTextField getJtCustomerName() {
		if(jtCustomerName==null){
			jtCustomerName=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtCustomerName;
	}
	private JLabel getCustomerAddr() {
		if(customerAddr==null){
			this.customerAddr=new JLabel("�˿͵�ַ:");
		}
		return customerAddr;
	}
	private JTextField getJtCustomerAddr() {
		if(jtCustomerAddr==null){
			jtCustomerAddr=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtCustomerAddr;
	}
	private JLabel getCustomerPhone() {
		if(customerPhone==null){
			this.customerPhone=new JLabel("�˿���ϵ�绰:");
		}
		return customerPhone;
	}
	private JTextField getJtCustomerPhone() {
		if(jtCustomerPhone==null){
			jtCustomerPhone=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtCustomerPhone;
	}
	private JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("ȷ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doCustomerInfo(flag);
				}

			});
		}
		return jb;
	}
	/**
	 * �˿���Ϣ��������������
	 * @param flag
	 */
	public void doCustomerInfo(String flag) {
		String jtCustomerIdTxt=jtCustomerId.getText().trim();
		String jtCustomerNameTxt=jtCustomerName.getText().trim();
		String jtCustomerAddrTxt=jtCustomerAddr.getText().trim();
		String jtCustomerPhoneTxt=jtCustomerPhone.getText().trim();
		
		if(null==jtCustomerIdTxt||"".equals(jtCustomerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "�˿��˺Ų���Ϊ�գ�");
			jtCustomerId.requestFocus();
			return;
		}
		if(null==jtCustomerNameTxt||"".equals(jtCustomerNameTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "�˿����Ʋ���Ϊ�գ�");
			jtCustomerName.requestFocus();
			return;
		}
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);
			stat=conn.createStatement();
			String sql01="select * from t_customer where customer_id='"+jtCustomerIdTxt+"'";
			rs=stat.executeQuery(sql01);

			if("insert".equals(flag)){
				if(rs.next()){
					JOptionPane.showMessageDialog(CustomerInfo.this, "�ù˿��Ѵ��ڣ�����������");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
				String sqlInsert="insert into t_customer (customer_id,customer_name,customer_phone,customer_addr) values ('"+jtCustomerIdTxt+"','"+jtCustomerNameTxt+"','"+jtCustomerPhoneTxt+"','"+jtCustomerAddrTxt+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(CustomerInfo.this, "�����ɹ�");
					CustomerInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerInfo.this, "����ʧ��");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
			}
			if("update".equals(flag)){
				if(!rs.next()){
					JOptionPane.showMessageDialog(CustomerInfo.this, "�ù˿Ͳ����ڣ�����������");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
				}
				String sqlUpdate="update t_customer set customer_name='"+jtCustomerNameTxt+"', customer_phone='"+jtCustomerPhoneTxt+"',customer_addr='"+jtCustomerAddrTxt+"'" +
						" where customer_id='"+jtCustomerIdTxt+"'";

				int result=stat.executeUpdate(sqlUpdate);
				if(result==1){
					JOptionPane.showMessageDialog(CustomerInfo.this, "���³ɹ�");
					CustomerInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(CustomerInfo.this, "����ʧ��");
					jtCustomerId.setText("");
					jtCustomerName.setText("");
					jtCustomerAddr.setText("");
					jtCustomerPhone.setText("");
					return;
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
	/**
	 * �˿���Ϣɾ��
	 */
	public void deleteCustomer(){
		this.setSize(500,300);
		this.setTitle("�˿���Ϣɾ��");
		addConpantDelete();
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	public void addConpantDelete() {
		this.setLayout(new FlowLayout());//��ʽ����
		this.add(this.getPanConpanents());
		
	}
	public JPanel getPanConpanents() {
		if(deleteCustomer==null){
			this.deleteCustomer=new JPanel(new GridLayout(2,2,10,10));
			this.deleteCustomer.add(this.getCustomerId());
			this.deleteCustomer.add(this.getTxCustomerId());
			this.deleteCustomer.add(this.getBtnDelete());
		}
		return deleteCustomer;
	}
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("ɾ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCustomerInfo();
				}
			});
		}
		return jb;
	}
	/**
	 * ɾ���˿���Ϣ������
	 */
	public void deleteCustomerInfo() {
		String jtCustomerIdTxt=jtCustomerId.getText().trim();
		if(null==jtCustomerIdTxt||"".equals(jtCustomerIdTxt)) {
			JOptionPane.showMessageDialog(CustomerInfo.this, "�˿��˺Ų���Ϊ�գ�");
			jtCustomerId.requestFocus();
			return;
		}
		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();
			String sql01="select * from t_customer where customer_id='"+jtCustomerIdTxt+"'";
			rs=stat.executeQuery(sql01);
			if(!rs.next()){
				JOptionPane.showMessageDialog(CustomerInfo.this, "�ù˿���Ϣ�����ڣ�����������");
				jtCustomerId.setText("");
				return;
			}
			String sql=" delete from t_customer where customer_id='"+jtCustomerIdTxt+"' ";
			int ret=stat.executeUpdate(sql);
			if(ret==1){
				JOptionPane.showMessageDialog(CustomerInfo.this, "ɾ���ɹ�");
				CustomerInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(CustomerInfo.this, "ɾ��ʧ��");
				jtCustomerId.requestFocus();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//�ر����ݿ�������Դ
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
