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
 *��Ӧ����Ϣά��
 */
public class SupplierInfo extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTable jt = null;
	private JScrollPane jsp = null;
	private JPanel panConpanents,deleteSupplier;
	private JLabel supplierId,supplierName,supplierAddr,supplierPhone,supplierIntroduc;
	private JTextField jtSupplierId,jtSupplierName,jtSupplierAddr,jtSupplierPhone,jtSupplierIntroduc;
	private JButton jb;
	@SuppressWarnings("rawtypes")
	public  Vector rowData, columnName; 
	/**
	 * ��ѯ��Ӧ����Ϣ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void querySupplier(){
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

			String sql="select sup_id, sup_name, sup_addr, sup_phone, sup_introduc  from t_supplier";
			rs = stat.executeQuery(sql);

			ResultSetMetaData data = rs.getMetaData();
			columnName = new Vector();

			//�������
			columnName.add("��Ӧ���˺�");
			columnName.add("��Ӧ������");
			columnName.add("��Ӧ�̵�ַ");
			columnName.add("��Ӧ����ϵ�绰");
			columnName.add("��Ӧ�̼��");

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
		this.setTitle("��Ӧ����Ϣ��ѯ");  
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
				int answer=JOptionPane.showConfirmDialog(SupplierInfo.this, "ȷ�Ϲرգ�");
				if(answer==JOptionPane.OK_OPTION){
					SupplierInfo.this.dispose();
				}
			}
		});
	}

	/**
	 * ������Ӧ����Ϣ
	 */
	public void insertSupplier(){
		String flag="insert";
		this.setSize(500,300);
		this.setTitle("��Ӧ������");
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
			this.panConpanents.add(this.getSupplierName());
			this.panConpanents.add(this.getTxSupplierName());
			this.panConpanents.add(this.getSupplierAddr());
			this.panConpanents.add(this.getTxSupplierAddr());
			this.panConpanents.add(this.getSupplierPhone());
			this.panConpanents.add(this.getTxSupplierPhone());
			this.panConpanents.add(this.getSupplierIntroduc());
			this.panConpanents.add(this.getTxSupplierIntroduc());
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
	//��Ӧ�������ı�
	public JLabel getSupplierName(){
		if(supplierName==null){
			this.supplierName=new JLabel("��Ӧ������:");
		}
		return supplierName;
	}
	//��Ӧ�����������
	public JTextField getTxSupplierName() {
		if(jtSupplierName==null){
			jtSupplierName=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSupplierName;
	}
	//��Ӧ�̵�ַ�ı�
	public JLabel getSupplierAddr(){
		if(supplierAddr==null){
			this.supplierAddr=new JLabel("��Ӧ�̵�ַ:");
		}
		return supplierAddr;
	}
	//��Ӧ�̵�ַ�����
	public JTextField getTxSupplierAddr() {
		if(jtSupplierAddr==null){
			jtSupplierAddr=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSupplierAddr;
	}
	//��Ӧ�̵绰�ı�
	public JLabel getSupplierPhone(){
		if(supplierPhone==null){
			this.supplierPhone=new JLabel("��Ӧ����ϵ�绰:");
		}
		return supplierPhone;
	}
	//��Ӧ�̵绰�����
	public JTextField getTxSupplierPhone() {
		if(jtSupplierPhone==null){
			jtSupplierPhone=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSupplierPhone;
	}
	//��Ӧ�̵绰�ı�
	public JLabel getSupplierIntroduc(){
		if(supplierIntroduc==null){
			this.supplierIntroduc=new JLabel("��Ӧ�̼��:");
		}
		return supplierIntroduc;
	}
	//��Ӧ�̵绰�����
	public JTextField getTxSupplierIntroduc() {
		if(jtSupplierIntroduc==null){
			jtSupplierIntroduc=new JTextField(20);//����һ������ָ���������µĿ� TextField
		}
		return jtSupplierIntroduc;
	}
	//ȷ����ť
	public JButton getBtnSubmit(final String flag) {
		if(jb==null){
			jb=new JButton("ȷ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertSupplierInfo(flag);
				}
			});
		}
		return jb;
	}


	public void insertSupplierInfo(String flag){

		String supplierIdTxt=jtSupplierId.getText().trim();
		String jtSupplierNameTxt=jtSupplierName.getText().trim();
		String jtSupplierAddrTxt=jtSupplierAddr.getText().trim();
		String jtSupplierPhoneTxt=jtSupplierPhone.getText().trim();
		String jtSupplierIntroducTxt=jtSupplierIntroduc.getText().trim();



		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierInfo.this, "��Ӧ���˺Ų���Ϊ�գ�");
			jtSupplierId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		ResultSet rs=null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection(url);

			stat=conn.createStatement();

			String sqlQuery="select * from t_supplier where sup_id='"+supplierIdTxt+"'";
			rs=stat.executeQuery(sqlQuery);
			if("insert".equals(flag)){//������Ӧ����Ϣ����
				if(rs.next()){
					JOptionPane.showMessageDialog(SupplierInfo.this, "�ù�Ӧ���Ѵ��ڣ�����������");
					jtSupplierId.requestFocus();
					jtSupplierName.requestFocus();
					jtSupplierAddr.requestFocus();
					jtSupplierPhone.requestFocus();
					jtSupplierIntroduc.requestFocus();
					return;
				}else{
					String sqlInsert=" insert into t_supplier (sup_id,sup_name,sup_addr,sup_phone,sup_introduc) values ("
						+ " '"+supplierIdTxt+"','"+jtSupplierNameTxt+"','"+jtSupplierAddrTxt+"','"+jtSupplierPhoneTxt+"','"+jtSupplierIntroducTxt+"')";

					int ret=stat.executeUpdate(sqlInsert);
					if(ret==1){
						JOptionPane.showMessageDialog(SupplierInfo.this, "�����ɹ�");
						SupplierInfo.this.dispose();
					}

				}
			}else if("update".equals(flag)){//���¹�Ӧ����Ϣ����

				if((null==jtSupplierNameTxt||"".equals(jtSupplierNameTxt))&&(null==jtSupplierAddrTxt||"".equals(jtSupplierAddrTxt))
						&&(null==jtSupplierPhoneTxt||"".equals(jtSupplierPhoneTxt))&&(null==jtSupplierIntroducTxt||"".equals(jtSupplierIntroducTxt))){
					JOptionPane.showMessageDialog(SupplierInfo.this, "��������޸Ĺ�Ӧ����Ϣ");
					return;
				}


				if(rs.next()){
					StringBuffer sb=new StringBuffer("update t_supplier set");
					if(null!=jtSupplierNameTxt&&!"".equals(jtSupplierNameTxt)){
						sb.append(" sup_name='"+jtSupplierNameTxt+"'");
						if(null!=jtSupplierAddrTxt&&!"".equals(jtSupplierAddrTxt)){
							sb.append(",sup_addr='"+jtSupplierAddrTxt+"'");
						}
						if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
							sb.append(",sup_phone='"+jtSupplierPhoneTxt+"'");
						}
						if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
							sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
						}
					}else{
						if(null!=jtSupplierAddrTxt&&!"".equals(jtSupplierAddrTxt)){
							sb.append(" sup_addr='"+jtSupplierAddrTxt+"'");
							if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
								sb.append(",sup_phone='"+jtSupplierPhoneTxt+"'");
							}
							if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
								sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
							}
						}else{
							if(null!=jtSupplierPhoneTxt&&!"".equals(jtSupplierPhoneTxt)){
								sb.append(" sup_phone='"+jtSupplierPhoneTxt+"'");
								if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
									sb.append(",sup_introduc='"+jtSupplierIntroducTxt+"'");
								}
								
							}else{
								if(null!=jtSupplierIntroducTxt&&!"".equals(jtSupplierIntroducTxt)){
									sb.append(" sup_introduc='"+jtSupplierIntroducTxt+"' ");
								}
							}
						}
					}
					sb.append(" where sup_id='"+supplierIdTxt+"'");
					int ret=stat.executeUpdate(sb.toString());
					if(ret==1){
						JOptionPane.showMessageDialog(SupplierInfo.this, "���³ɹ�");
						SupplierInfo.this.dispose();
					}else{
						JOptionPane.showMessageDialog(SupplierInfo.this, "����ʧ��");
						return;
					}

				}else{
					JOptionPane.showMessageDialog(SupplierInfo.this, "�ù�Ӧ�̲����ڣ�����������");
					jtSupplierId.requestFocus();
					jtSupplierName.requestFocus();
					jtSupplierAddr.requestFocus();
					jtSupplierPhone.requestFocus();
					jtSupplierIntroduc.requestFocus();
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
	 * �޸Ĺ�Ӧ����Ϣ
	 */
	public void updateSupplier(){
		String flag="update";
		this.setSize(500,300);
		this.setTitle("��Ӧ�̸���");
		addConpant(flag);
		this.setLocationRelativeTo(null);//����
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
	}
	public void addConpant(String flag) {
		this.setLayout(new FlowLayout());//��ʽ����
		this.add(this.getPanConpanents(flag));

	}
	/**
	 * ɾ����Ӧ����Ϣ
	 */
	public void deleteSupplier(){
		
		this.setSize(500,300);
		this.setTitle("��Ӧ����Ϣɾ��");
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
		if(deleteSupplier==null){
			this.deleteSupplier=new JPanel(new GridLayout(2,1,10,10));
			this.deleteSupplier.add(this.getSupplierId());
			this.deleteSupplier.add(this.getTxSupplierId());
			this.deleteSupplier.add(this.getBtnDelete());
		}
		return deleteSupplier;
	}
	//ɾ����ť
	public JButton getBtnDelete() {
		if(jb==null){
			jb=new JButton("ɾ��");
			jb.setFont(new Font("����",Font.BOLD,14));
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteSupplierInfo();
				}

			});
		}
		return jb;
	}
	/**
	 * ɾ����Ӧ����Ϣ������
	 */
	public void deleteSupplierInfo() {
		
		String supplierIdTxt=jtSupplierId.getText().trim();
		
		if(null==supplierIdTxt||"".equals(supplierIdTxt)) {
			JOptionPane.showMessageDialog(SupplierInfo.this, "��Ӧ���˺Ų���Ϊ�գ�");
			jtSupplierId.requestFocus();
			return;
		}

		Connection conn= null;
		Statement stat = null;
		//mysql���ݿ�����url
		String url="jdbc:mysql://localhost:3306/graduation_qiao?"
			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
			//��������
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//��ȡ����
				conn=DriverManager.getConnection(url);

				stat=conn.createStatement();

				String sql=" delete from t_supplier where sup_id='"+supplierIdTxt+"' ";
				int ret=stat.executeUpdate(sql);
				if(ret==1){
					JOptionPane.showMessageDialog(SupplierInfo.this, "ɾ���ɹ�");
					SupplierInfo.this.dispose();
				}else{
					JOptionPane.showMessageDialog(SupplierInfo.this, "ɾ��ʧ��");
					jtSupplierId.requestFocus();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
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
