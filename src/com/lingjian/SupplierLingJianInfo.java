package com.lingjian;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *��Ӧ�������Ϣά�� 
 */
public class SupplierLingJianInfo extends JFrame{


	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JPanel panConpanents,deleteSupplierLj;
	private JLabel supplierId,spareId,number;
	private JTextField jtSupplierId,jtSpareId,jtNumber;
	private JButton jb;
	public  Vector rowData, columnName; 
	/**
	 * ��ѯ��Ӧ�������Ϣ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void querySupplierLj(){
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

			String sql="select *  from t_sup_spare";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			columnName.add("��Ӧ���˺�");
			columnName.add("�����");
			columnName.add("��Ӧ��");

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
		this.setTitle("��Ӧ�������Ϣ��ѯ");  
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
				int answer=JOptionPane.showConfirmDialog(SupplierLingJianInfo.this, "ȷ�Ϲرգ�");
				if(answer==JOptionPane.OK_OPTION){
					SupplierLingJianInfo.this.dispose();
				}
			}
		});
	}

	/**
	 * ��Ӧ�������Ϣ����
	 */
	public void insertSupplierLj(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("��Ӧ�������Ϣ����");
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
			this.panConpanents.add(this.getSupplierId());
			this.panConpanents.add(this.getTxSupplierId());
			this.panConpanents.add(this.getSpareId());
			this.panConpanents.add(this.getJtSpareId());
			this.panConpanents.add(this.getNumber());
			this.panConpanents.add(this.getJtNumber());
			this.panConpanents.add(this.getBtnSubmit(flag));
		}

		return panConpanents;
	}
	//��Ӧ���˺��ı�
	public JLabel getSupplierId(){
		if(supplierId==null){
			this.supplierId=new JLabel("��Ӧ���˺�:");
		}
		return supplierId;
	}
	//��Ӧ���˺������
	public JTextField getTxSupplierId() {
		if(jtSupplierId==null){
			jtSupplierId=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSupplierId;
	}
	//������ı�
	public JLabel getSpareId(){
		if(spareId==null){
			this.spareId=new JLabel("�����:");
		}
		return spareId;
	}
	//����������
	public JTextField getJtSpareId() {
		if(jtSpareId==null){
			jtSpareId=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSpareId;
	}
	//��Ӧ���ı�
	public JLabel getNumber(){
		if(number==null){
			this.number=new JLabel("��Ӧ��:");
		}
		return number;
	}
	//��Ӧ�������
	public JTextField getJtNumber() {
		if(jtNumber==null){
			jtNumber=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtNumber;
	}
	//ȷ����ť
	public JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("ȷ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					doSuppplierLjInfo(flag);
				}
			});
		}
		return jb;
	}
	/**
	 * ��Ӧ�������Ϣ����������
	 * @param flag
	 */
	public void doSuppplierLjInfo(String flag) {

		String supplierIdTxt=jtSupplierId.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		String numberTxt=jtNumber.getText().trim();

		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "��Ӧ���˺Ų���Ϊ�գ�");
			jtSupplierId.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "����Ų���Ϊ�գ�");
			jtSpareId.requestFocus();
			return;
		}
		if(null==numberTxt||"".equals(numberTxt)) {
			JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "��Ӧ������Ϊ�գ�");
			jtNumber.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		Statement stat1 = null;
		Statement stat2 = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
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
			rs2=stat.executeQuery(sql02);
			if(rs2.next()){
				System.out.println(rs2.getString(1));
			}else{
				JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�������Ϣ�����ڣ�����������");
				jtNumber.setText("");
				jtSpareId.setText("");
				jtSupplierId.setText("");
				return;
			}

			String sql03="select * from t_supplier where sup_id='"+supplierIdTxt+"'";
			rs3=stat2.executeQuery(sql03);
			if(rs3.next()){
				System.out.println(rs3.getString(1));
			}else{
				JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�ù�Ӧ�̲����ڣ�����������");
				jtNumber.setText("");
				jtSpareId.setText("");
				jtSupplierId.setText("");
				return;
			}
			
			String sql01="select * from t_sup_spare where sup_id='"+supplierIdTxt+"' and spare_id='"+spareIdTxt+"'";
			rs1=stat1.executeQuery(sql01);
			if("insert".equals(flag)){
				if(rs1.next()){
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�ù�Ӧ�������Ϣ�Ѵ��ڣ�����������");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtSupplierId.setText("");
					return;
				}
				String sqlInsert="insert into t_sup_spare (sup_id,spare_id,number) values ('"+supplierIdTxt+"','"+spareIdTxt+"','"+numberTxt+"')";
				int ret=stat.executeUpdate(sqlInsert);
				if(ret==1){
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�����ɹ�");
					SupplierLingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "����ʧ��");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtSupplierId.setText("");
					return;
				}
			}
			if("update".equals(flag)){
				if(rs1.next()){
					System.out.println(rs1.getString(1));
				}else{
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�ù�Ӧ�������Ϣ�����ڣ�����������");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtSupplierId.setText("");
					return;
				}

				String sqlUpdate="update t_sup_spare set number='"+numberTxt+"' where sup_id='"+supplierIdTxt+"' and spare_id='"+spareIdTxt+"'";

				int result=stat.executeUpdate(sqlUpdate);
				if(result==1){
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "���³ɹ�");
					SupplierLingJianInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "����ʧ��");
					jtNumber.setText("");
					jtSpareId.setText("");
					jtSupplierId.setText("");
					return;
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر����ݿ�������Դ
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs3 != null) {
				try {
					rs3.close();
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

	/**
	 * ��Ӧ�������Ϣ�޸� 
	 */
	public void UpdateSupplierLj(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("��Ӧ�������Ϣ�޸�");
		addConpants(flag);
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	/**
	 * ��Ӧ�������Ϣɾ��
	 */
	public void deleteSupplierLj(){
		this.setSize(500,300);
		this.setTitle("��Ӧ�������Ϣɾ��");
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
		if(deleteSupplierLj==null){
			this.deleteSupplierLj=new JPanel(new GridLayout(3,2,10,10));
			this.deleteSupplierLj.add(this.getSupplierId());
			this.deleteSupplierLj.add(this.getTxSupplierId());
			this.deleteSupplierLj.add(this.getSpareId());
			this.deleteSupplierLj.add(this.getJtSpareId());
			this.deleteSupplierLj.add(this.getBtnDelete());
		}
		return deleteSupplierLj;
	}
	//ɾ����ť
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("ɾ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteSupplierInfoLj();
				}

			});
		}
		return jb;
	}
	/**
	 * ɾ����Ӧ�������Ϣ������
	 */
	public void deleteSupplierInfoLj() {
		String supplierIdTxt=jtSupplierId.getText().trim();
		String spareIdTxt=jtSpareId.getText().trim();
		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "��Ӧ���˺Ų���Ϊ�գ�");
			jtSupplierId.requestFocus();
			return;
		}
		if(null==spareIdTxt||"".equals(spareIdTxt)) {
			JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "����Ų���Ϊ�գ�");
			jtSpareId.requestFocus();
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
			String sql01="select * from t_sup_spare where sup_id='"+supplierIdTxt+"' and spare_id='"+spareIdTxt+"'";
			String sql=" delete from t_sup_spare where sup_id='"+supplierIdTxt+"' and spare_id='"+spareIdTxt+"' ";
			rs=stat.executeQuery(sql01);
			if(!rs.next()){
				JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "�ù�Ӧ�������Ϣ�����ڣ�����������");
				jtSpareId.setText("");
				jtSupplierId.setText("");
				return;
			}
			int ret=stat.executeUpdate(sql);
			if(ret==1){
				JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "ɾ���ɹ�");
				SupplierLingJianInfo.this.dispose();
			}else{
				JOptionPane.showMessageDialog(SupplierLingJianInfo.this, "ɾ��ʧ��");
				jtSupplierId.requestFocus();
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
