package controller.user;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManagerUi extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtAmount;
    private JTextField txtPrice;

    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ManagerUi frame = new ManagerUi();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ManagerUi() {

        setTitle("商品管理系統");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 820, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        //-------------------Label-------------------

        JLabel lblTitle = new JLabel("商品管理系統");
        lblTitle.setBounds(350, 10, 150, 25);
        contentPane.add(lblTitle);

        JLabel lblId = new JLabel("商品ID");
        lblId.setBounds(30, 60, 80, 25);
        contentPane.add(lblId);

        JLabel lblName = new JLabel("商品名稱");
        lblName.setBounds(30, 100, 80, 25);
        contentPane.add(lblName);

        JLabel lblAmount = new JLabel("商品庫存");
        lblAmount.setBounds(30, 140, 80, 25);
        contentPane.add(lblAmount);

        JLabel lblPrice = new JLabel("商品價格");
        lblPrice.setBounds(30, 180, 80, 25);
        contentPane.add(lblPrice);

        //-------------------TextField-------------------

        txtProductId = new JTextField();
        txtProductId.setBounds(120, 60, 180, 25);
        contentPane.add(txtProductId);

        txtProductName = new JTextField();
        txtProductName.setBounds(120, 100, 180, 25);
        contentPane.add(txtProductName);

        txtAmount = new JTextField();
        txtAmount.setBounds(120, 140, 180, 25);
        contentPane.add(txtAmount);

        txtPrice = new JTextField();
        txtPrice.setBounds(120, 180, 180, 25);
        contentPane.add(txtPrice);

        //-------------------Button-------------------

        JButton btnInsert = new JButton("新增商品");
        btnInsert.setBounds(350, 60, 120, 30);
        contentPane.add(btnInsert);

        JButton btnUpdate = new JButton("修改商品");
        btnUpdate.setBounds(500, 60, 120, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("刪除商品");
        btnDelete.setBounds(650, 60, 120, 30);
        contentPane.add(btnDelete);

        JButton btnSelectAll = new JButton("查詢全部");
        btnSelectAll.setBounds(350, 110, 120, 30);
        contentPane.add(btnSelectAll);

        JButton btnSelectId = new JButton("依ID查詢");
        btnSelectId.setBounds(500, 110, 120, 30);
        contentPane.add(btnSelectId);

        JButton btnClear = new JButton("清空");
        btnClear.setBounds(650, 110, 120, 30);
        contentPane.add(btnClear);

        //-------------------Table-------------------

        model = new DefaultTableModel();

        model.addColumn("商品ID");
        model.addColumn("商品名稱");
        model.addColumn("庫存");
        model.addColumn("價格");
        model.addColumn("建立時間");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 240, 740, 290);
        contentPane.add(scrollPane);

    }

}
