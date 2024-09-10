package com.lingjian;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *ϵͳ������/�˵�����
 */
public class LingJianMainPage extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel panBg;
	private JMenuBar mbar;
	private JMenu menuTrans, menuSupplier,menuCustomer,menuLingJian;
	private JMenuItem itemSupplierQuery,itemSupplierInsert,itemSupplierUpdate,itemSupplierDelete,
	itemSupplierLingJianQuery,itemSupplierLingJianInsert,itemSupplierLingJianUpdate,itemSupplierLingJianDelete
	,itemCustomerQuery,itemCustomerInsert,itemCustomerUpdate,itemCustomerDelete,
	itemCustomerLingJianQuery,itemCustomerLingJianInsert,itemCustomerLingJianUpdate,itemCustomerLingJianDelete,
	itemLingJianQuery,itemLingJianInsert,itemLingJianUpdate,itemLingJianDelete,
	itemTrans;

	//��Ӧ�̹���˵�
	private JMenu getMenuSupplier() {
		if(menuSupplier==null){
			this.menuSupplier=new JMenu("��Ӧ�̹���");
			this.menuSupplier.add(this.getItemSuplierQuery());
			this.menuSupplier.add(this.getItemSuplierInsert());
			this.menuSupplier.add(this.getItemSuplierUpdate());
			this.menuSupplier.add(this.getItemSuplierDelete());
			this.menuSupplier.add(this.getItemSuplierLingJianQuery());
			this.menuSupplier.add(this.getItemSuplierLingJianInsert());
			this.menuSupplier.add(this.getItemSuplierLingJianUpdate());
			this.menuSupplier.add(this.getItemSuplierLingJianDelete());
		}
		return menuSupplier;
	}
	//��Ӧ����Ϣ��ѯ�˵�
	private JMenuItem getItemSuplierQuery() {
		if(itemSupplierQuery==null){
			this.itemSupplierQuery=new JMenuItem("��Ӧ����Ϣ��ѯ");
			this.itemSupplierQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ����Ϣ��ѯ����
					SupplierInfo sup=new SupplierInfo();
					sup.querySupplier();
				}
			});
		}
		return itemSupplierQuery;
	}
	//��Ӧ����Ϣ�����˵�
	private JMenuItem getItemSuplierInsert() {
		if(itemSupplierInsert==null){
			this.itemSupplierInsert=new JMenuItem("��Ӧ����Ϣ����");
			this.itemSupplierInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ������ҳ��
					SupplierInfo sup=new SupplierInfo();
					sup.insertSupplier();
				}
			});
		}
		return itemSupplierInsert;
	}
	//��Ӧ����Ϣ�޸Ĳ˵�
	private JMenuItem getItemSuplierUpdate() {
		if(itemSupplierUpdate==null){
			this.itemSupplierUpdate=new JMenuItem("��Ӧ����Ϣ�޸�");
			this.itemSupplierUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ�̸��½���
					SupplierInfo sup=new SupplierInfo();
					sup.updateSupplier();
				}
			});
		}
		return itemSupplierUpdate;
	}
	//��Ӧ����Ϣɾ���˵�
	private JMenuItem getItemSuplierDelete() {
		if(itemSupplierDelete==null){
			this.itemSupplierDelete=new JMenuItem("��Ӧ����Ϣɾ��");
			this.itemSupplierDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ��ɾ������
					SupplierInfo sup=new SupplierInfo();
					sup.deleteSupplier();
				}
			});
		}
		return itemSupplierDelete;
	}
	//��Ӧ�������Ϣ��ѯ�˵�
	private JMenuItem getItemSuplierLingJianQuery() {
		if(itemSupplierLingJianQuery==null){
			this.itemSupplierLingJianQuery=new JMenuItem("��Ӧ�������Ϣ��ѯ");
			this.itemSupplierLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ�������Ϣ��ѯ����
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.querySupplierLj();
				}
			});
		}
		return itemSupplierLingJianQuery;
	}
	//��Ӧ�������Ϣ�����˵�
	private JMenuItem getItemSuplierLingJianInsert() {
		if(itemSupplierLingJianInsert==null){
			this.itemSupplierLingJianInsert=new JMenuItem("��Ӧ�������Ϣ����");
			this.itemSupplierLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ�������Ϣ��������
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.insertSupplierLj();
				}
			});
		}
		return itemSupplierLingJianInsert;
	}
	//��Ӧ�������Ϣ���²˵�
	private JMenuItem getItemSuplierLingJianUpdate() {
		if(itemSupplierLingJianUpdate==null){
			this.itemSupplierLingJianUpdate=new JMenuItem("��Ӧ�������Ϣ����");
			this.itemSupplierLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ�������Ϣ���½���
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.UpdateSupplierLj();
				}
			});
		}
		return itemSupplierLingJianUpdate;
	}
	//��Ӧ�������Ϣɾ���˵�
	private JMenuItem getItemSuplierLingJianDelete() {
		if(itemSupplierLingJianDelete==null){
			this.itemSupplierLingJianDelete=new JMenuItem("��Ӧ�������Ϣɾ��");
			this.itemSupplierLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹�Ӧ�������Ϣɾ������
					SupplierLingJianInfo sl=new SupplierLingJianInfo();
					sl.deleteSupplierLj();
				}
			});
		}
		return itemSupplierLingJianDelete;
	}



	private JMenu getMenuCustomer() {
		if(menuCustomer==null){
			this.menuCustomer=new JMenu("�˿���Ϣ����");
			this.menuCustomer.add(this.getCustomerQuery());
			this.menuCustomer.add(this.getCustomerInsert());
			this.menuCustomer.add(this.getCustomerUpdate());
			this.menuCustomer.add(this.getCustomerDelete());
			this.menuCustomer.add(this.getCoutomerLingJianQuery());
			this.menuCustomer.add(this.getCoutomerLingJianInsert());
			this.menuCustomer.add(this.getCoutomerLingJianUpdate());
			this.menuCustomer.add(this.getCoutomerLingJianDelete());
		}
		return menuCustomer;
	}
	//�˿���Ϣ��ѯ�˵�
	private JMenuItem getCustomerQuery() {
		if(itemCustomerQuery==null){
			this.itemCustomerQuery=new JMenuItem("�˿���Ϣ��ѯ");
			this.itemCustomerQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿���Ϣ��ѯ����
					CustomerInfo sl=new CustomerInfo();
					sl.queryCustomer();
				}
			});
		}
		return itemCustomerQuery;
	}
	//�˿���Ϣ�����˵�
	private JMenuItem getCustomerInsert() {
		if(itemCustomerInsert==null){
			this.itemCustomerInsert=new JMenuItem("�˿���Ϣ����");
			this.itemCustomerInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿���Ϣ��������
					CustomerInfo sl=new CustomerInfo();
					sl.insertCustomer();
				}
			});
		}
		return itemCustomerInsert;
	}
	//�˿���Ϣ���²˵�
	private JMenuItem getCustomerUpdate() {
		if(itemCustomerUpdate==null){
			this.itemCustomerUpdate=new JMenuItem("�˿���Ϣ����");
			this.itemCustomerUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿���Ϣ���½���
					CustomerInfo sl=new CustomerInfo();
					sl.updateCustomer();
				}
			});
		}
		return itemCustomerUpdate;
	}
	//�˿���Ϣɾ���˵�
	private JMenuItem getCustomerDelete() {
		if(itemCustomerDelete==null){
			this.itemCustomerDelete=new JMenuItem("�˿���Ϣɾ��");
			this.itemCustomerDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿���Ϣɾ������
					CustomerInfo sl=new CustomerInfo();
					sl.deleteCustomer();
				}
			});
		}
		return itemCustomerDelete;
	}

	//�˿������Ϣ��ѯ�˵�
	private JMenuItem getCoutomerLingJianQuery() {
		if(itemCustomerLingJianQuery==null){
			this.itemCustomerLingJianQuery=new JMenuItem("�˿������Ϣ��ѯ");
			this.itemCustomerLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿������Ϣ��ѯ����
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.queryCustomer();
				}
			});
		}
		return itemCustomerLingJianQuery;
	}
	//�˿������Ϣ�����˵�
	private JMenuItem getCoutomerLingJianInsert() {
		if(itemCustomerLingJianInsert==null){
			this.itemCustomerLingJianInsert=new JMenuItem("�˿������Ϣ����");
			this.itemCustomerLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿������Ϣ��������
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.insertCustomerLj();
				}
			});
		}
		return itemCustomerLingJianInsert;
	}
	//�˿������Ϣ�޸Ĳ˵�
	private JMenuItem getCoutomerLingJianUpdate() {
		if(itemCustomerLingJianUpdate==null){
			this.itemCustomerLingJianUpdate=new JMenuItem("�˿������Ϣ�޸�");
			this.itemCustomerLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿������Ϣ�޸Ľ���
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.updateCustomer();
				}
			});
		}
		return itemCustomerLingJianUpdate;
	}
	//�˿������Ϣɾ���˵�
	private JMenuItem getCoutomerLingJianDelete() {
		if(itemCustomerLingJianDelete==null){
			this.itemCustomerLingJianDelete=new JMenuItem("�˿������Ϣɾ��");
			this.itemCustomerLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪹˿������Ϣ�޸Ľ���
					CustomerLingJianInfo sl=new CustomerLingJianInfo();
					sl.deleteCustomer();
				}
			});
		}
		return itemCustomerLingJianDelete;
	}

	private JMenu getMenuLingJian() {
		if(menuLingJian==null){
			this.menuLingJian=new JMenu("�����Ϣ����");
			this.menuLingJian.add(this.getItemLingJianQuery());
			this.menuLingJian.add(this.getItemLingJianInsert());
			this.menuLingJian.add(this.getItemLingJianUpdate());
			this.menuLingJian.add(this.getItemLingJianDelete());
		}
		return menuLingJian;
	}
	//�����Ϣ��ѯ�˵�
	private JMenuItem getItemLingJianQuery() {
		if(itemLingJianQuery==null){
			this.itemLingJianQuery=new JMenuItem("�����Ϣ��ѯ");
			this.itemLingJianQuery.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�������Ϣ��ѯ����
					LingJianInfo lj=new LingJianInfo();
					lj.queryLingJian();
				}
			});
		}
		return itemLingJianQuery;
	}
	//�����Ϣ�����˵�
	private JMenuItem getItemLingJianInsert() {
		if(itemLingJianInsert==null){
			this.itemLingJianInsert=new JMenuItem("�����Ϣ����");
			this.itemLingJianInsert.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�������Ϣ��������
					LingJianInfo lj=new LingJianInfo();
					lj.insertLingJian();
				}
			});
		}
		return itemLingJianInsert;
	}
	//�����Ϣ�޸Ĳ˵�
	private JMenuItem getItemLingJianUpdate() {
		if(itemLingJianUpdate==null){
			this.itemLingJianUpdate=new JMenuItem("�����Ϣ�޸�");
			this.itemLingJianUpdate.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�������Ϣ�޸Ľ���
					LingJianInfo lj=new LingJianInfo();
					lj.updateLingJian();
				}
			});
		}
		return itemLingJianUpdate;
	}
	//�����Ϣɾ���˵�
	private JMenuItem getItemLingJianDelete() {
		if(itemLingJianDelete==null){
			this.itemLingJianDelete=new JMenuItem("�����Ϣɾ��");
			this.itemLingJianDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�������Ϣɾ������
					LingJianInfo lj=new LingJianInfo();
					lj.deleteLingJian();
				}
			});
		}
		return itemLingJianDelete;
	}



	private JMenu getMenuTrans() {
		if(menuTrans==null){
			this.menuTrans=new JMenu("���׹���");
			this.menuTrans.add(this.getItemTrans());
		}
		return menuTrans;
	}
	//������Ϣά���˵�
	private JMenuItem getItemTrans() {
		if(itemTrans==null){
			this.itemTrans=new JMenuItem("������Ϣά��");
			this.itemTrans.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//�򿪽�����Ϣά������
					TransactionInfo trans=new TransactionInfo();
					trans.trans();
				}
			});
		}
		return itemTrans;
	}
	private JMenuBar getMbar() {
		if(mbar==null){
			this.mbar=new JMenuBar();
			this.mbar.add(this.getMenuSupplier());
			this.mbar.add(this.getMenuCustomer());
			this.mbar.add(this.getMenuLingJian());
			this.mbar.add(this.getMenuTrans());

		}
		return mbar;
	}
	public LingJianMainPage(){
		init();
	}
	private void init() {
		this.setSize(600, 400);
		this.setTitle("����������Ĺ���ϵͳ");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int sw=screenSize.width;
		int sh=screenSize.height;
		this.setLocation((sw-this.getWidth())/2,(sh-this.getHeight())/2);
		addConpants();
		this.setResizable(false);//���óߴ粻�ɱ�
		this.setVisible(true);
		//ȡ��JFrame�Ĺر�Ĭ�ϲ���,Ĭ�Ϲر�ȡ���������ص�ǰ���壬ȡ�����Լ�����
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer=JOptionPane.showConfirmDialog(LingJianMainPage.this, "ȷ�Ϲرգ�");
				if(answer==JOptionPane.OK_OPTION){
					LingJianMainPage.this.dispose();
				}
			}
		});

	}
	private void addConpants() {
		this.setLayout(new GridLayout(1,1));
		this.add(this.getPanBg());
		this.setJMenuBar(this.getMbar());;//������м��ز˵���
	}
	public JPanel getPanBg() {
		if(this.panBg==null){
			this.panBg=new BackgroundPanel();
		}
		return panBg;
	}
}
