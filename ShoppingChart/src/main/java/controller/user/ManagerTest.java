package controller.user;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

public class ManagerTest extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtAmount;
    private JTextField txtPrice;

    private JTable table;
    private DefaultTableModel model;

    private ProductService productService = new ProductServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ManagerTest frame = new ManagerTest();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ManagerTest() {

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

        //-------------------Listener-------------------

        // ===============================
        // 新增商品
        // ===============================
        btnInsert.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                String name = txtProductName.getText().trim();
                int amount = getInt(txtAmount);
                int price = getInt(txtPrice);

                if (name.isEmpty() || amount == -1 || price == -1) {
                    return;
                }

                Product product = new Product();
                product.setProductName(name);
                product.setProductAmount(amount);
                product.setProductPrice(price);

                Product created = productService.createProduct(product);

                if (created != null) {
                    JOptionPane.showMessageDialog(null, "新增成功，商品ID：" + created.getProductId());
                    showProductInTable(created);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "新增失敗");
                }
            }
        });

        // ===============================
        // 修改商品
        // ===============================
        btnUpdate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int id = getInt(txtProductId);

                if (id == -1) {
                    return;
                }

                String name = txtProductName.getText().trim();
                int amount = getInt(txtAmount);
                int price = getInt(txtPrice);

                if (name.isEmpty() || amount == -1 || price == -1) {
                    return;
                }

                Product product = new Product();
                product.setProductId(id);
                product.setProductName(name);
                product.setProductAmount(amount);
                product.setProductPrice(price);

                Product updated = productService.updateProduct(product);

                if (updated != null) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                    showProductInTable(updated);
                } else {
                    JOptionPane.showMessageDialog(null, "修改失敗，找不到該商品");
                }
            }
        });

        // ===============================
        // 刪除商品
        // ===============================
        btnDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int id = getInt(txtProductId);

                if (id == -1) {
                    return;
                }

                productService.deleteProduct(id);

                JOptionPane.showMessageDialog(null, "已刪除商品 ID：" + id);

                clearFields();
                model.setRowCount(0);
            }
        });

        // ===============================
        // 查詢全部
        // ===============================
        btnSelectAll.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                String data = productService.getAllProducts();

                System.out.println(data);

                // TODO: getAllProducts() 目前回傳 String，無法直接拆解填入表格的多列資料。
                // 建議改回傳 List<Product>，再用迴圈呼叫
                // model.addRow(new Object[]{ id, name, amount, price, createAt })
                // 才能讓 JTable 真正顯示所有商品。
                JOptionPane.showMessageDialog(null, "查詢結果已輸出到 Console，請查看主控台視窗。");
            }
        });

        // ===============================
        // 依ID查詢
        // ===============================
        btnSelectId.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int id = getInt(txtProductId);

                if (id == -1) {
                    return;
                }

                Product product = productService.getProductById(id);

                if (product != null) {
                    txtProductName.setText(product.getProductName());
                    txtAmount.setText(String.valueOf(product.getProductAmount()));
                    txtPrice.setText(String.valueOf(product.getProductPrice()));

                    showProductInTable(product);
                } else {
                    txtProductName.setText("查無商品");
                    txtAmount.setText("");
                    txtPrice.setText("");
                    model.setRowCount(0);
                }
            }
        });

        // ===============================
        // 清空
        // ===============================
        btnClear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                clearFields();
            }
        });
    }

    private int getInt(JTextField field) {

        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "請輸入正確數字");
            return -1;
        }
    }

    private void clearFields() {
        txtProductId.setText("");
        txtProductName.setText("");
        txtAmount.setText("");
        txtPrice.setText("");
    }

    private void showProductInTable(Product product) {
        model.setRowCount(0);
        model.addRow(new Object[] {
            product.getProductId(),
            product.getProductName(),
            product.getProductAmount(),
            product.getProductPrice(),
            product.getCreateAt()
        });
    }
}