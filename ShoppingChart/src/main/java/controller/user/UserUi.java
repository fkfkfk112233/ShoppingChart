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

import model.Cart;
import model.Product;
import model.Users;
import service.ProductService;
import service.CartService;

import service.impl.ProductServiceImpl;
import service.impl.CartServiceImpl;


public class UserUi extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtProductId;
    private JTextField txtProductName;
    private JTextField txtPrice;
    private JTextField txtQuantity;

    private JTable table;
    private DefaultTableModel model;

    // service
    private ProductService productService = new ProductServiceImpl();
    private CartService cartService = new CartServiceImpl();

    // 登入中的使用者，由 LoginUi 在登入成功後傳入
    private Users loginUser;
    private int loginUserId;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                // 測試用假帳號，正式流程應由 LoginUi 登入成功後
                // 把真實的 Users 物件傳進來，而不是在這裡自己造一個
                Users testUser = new Users();
                testUser.setUserId(1);

                UserUi frame = new UserUi(testUser);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UserUi(Users user) {

        this.loginUser = user;
        this.loginUserId = user.getUserId();

        setTitle("購物系統");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 620);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("購物系統");
        lblTitle.setBounds(400, 10, 120, 25);
        contentPane.add(lblTitle);

        JLabel lblProductId = new JLabel("商品ID");
        lblProductId.setBounds(30, 60, 80, 25);
        contentPane.add(lblProductId);

        JLabel lblProductName = new JLabel("商品名稱");
        lblProductName.setBounds(30, 100, 80, 25);
        contentPane.add(lblProductName);

        JLabel lblPrice = new JLabel("商品價格");
        lblPrice.setBounds(30, 140, 80, 25);
        contentPane.add(lblPrice);

        JLabel lblQuantity = new JLabel("購買數量");
        lblQuantity.setBounds(30, 180, 80, 25);
        contentPane.add(lblQuantity);

        txtProductId = new JTextField();
        txtProductId.setBounds(120, 60, 180, 25);
        contentPane.add(txtProductId);

        txtProductName = new JTextField();
        txtProductName.setBounds(120, 100, 180, 25);
        txtProductName.setEditable(false);
        contentPane.add(txtProductName);

        txtPrice = new JTextField();
        txtPrice.setBounds(120, 140, 180, 25);
        txtPrice.setEditable(false);
        contentPane.add(txtPrice);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(120, 180, 180, 25);
        contentPane.add(txtQuantity);

        JButton btnSearch = new JButton("查詢商品");
        btnSearch.setBounds(360, 60, 130, 30);
        contentPane.add(btnSearch);

        JButton btnAdd = new JButton("加入購物車");
        btnAdd.setBounds(520, 60, 130, 30);
        contentPane.add(btnAdd);

        JButton btnViewCart = new JButton("查看購物車");
        btnViewCart.setBounds(680, 60, 150, 30);
        contentPane.add(btnViewCart);

        JButton btnUpdate = new JButton("修改數量");
        btnUpdate.setBounds(360, 110, 130, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("移除商品");
        btnDelete.setBounds(520, 110, 130, 30);
        contentPane.add(btnDelete);

        JButton btnClear = new JButton("清空購物車");
        btnClear.setBounds(680, 110, 150, 30);
        contentPane.add(btnClear);

        JButton btnLogout = new JButton("登出");
        btnLogout.setBounds(680, 170, 150, 30);
        contentPane.add(btnLogout);

        model = new DefaultTableModel();
        model.addColumn("商品ID");
        model.addColumn("商品名稱");
        model.addColumn("單價");
        model.addColumn("數量");
        model.addColumn("小計");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 250, 820, 300);
        contentPane.add(scrollPane);

        // ===============================
        // 查詢商品
        // ===============================
        btnSearch.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int id = getInt(txtProductId);

                if (id == -1) {
                    return;
                }

                Product product = productService.getProductById(id);

                if (product != null) {
                    txtProductName.setText(product.getProductName());
                    txtPrice.setText(String.valueOf(product.getProductPrice()));

                    showProductInTable(product);
                } else {
                    txtProductName.setText("查無商品");
                    txtPrice.setText("");

                    model.setRowCount(0);
                }
            }
        });

        // ===============================
        // 加入購物車
        // ===============================
        btnAdd.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int productId = getInt(txtProductId);
                int quantity = getInt(txtQuantity);

                if (productId == -1 || quantity == -1) {
                    return;
                }

                Product product = productService.getProductById(productId);

                if (product != null) {
                    Cart cart = new Cart();

                    cart.setUserId(loginUserId);
                    cart.setProductId(productId);
                    cart.setQuantity(quantity);
                    cart.setSum(product.getProductPrice() * quantity);

                    cartService.addCart(cart);

                    refreshCart();
                }
            }
        });

        // ===============================
        // 查看購物車
        // ===============================
        btnViewCart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                refreshCart();
            }
        });

        // ===============================
        // 修改數量
        // ===============================
        btnUpdate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                int productId = getInt(txtProductId);
                int quantity = getInt(txtQuantity);

                if (productId == -1 || quantity == -1) {
                    return;
                }

                Product product = productService.getProductById(productId);

                if (product != null) {
                    Cart cart = new Cart();

                    cart.setUserId(loginUserId);
                    cart.setProductId(productId);
                    cart.setQuantity(quantity);
                    cart.setSum(product.getProductPrice() * quantity);

                    cartService.updateCart(cart);

                    refreshCart();
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

                cartService.removeProduct(loginUserId, id);

                refreshCart();
            }
        });

        // ===============================
        // 清空購物車
        // ===============================
        btnClear.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                cartService.clearCart(loginUserId);

                refreshCart();
            }
        });

        // ===============================
        // 登出
        // ===============================
        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                LoginUi login = new LoginUi();

                login.setVisible(true);

                dispose();
            }
        });
    }

    // 把查詢到的單一商品顯示在表格（scrollPane）裡
    private void showProductInTable(Product product) {
        model.setRowCount(0);
        model.addRow(new Object[] {
            product.getProductId(),
            product.getProductName(),
            product.getProductPrice(),
            "",   // 數量：還沒加入購物車，先留空
            ""    // 小計：還沒加入購物車，先留空
        });
    }

    private int getInt(JTextField field) {

        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null,
                "請輸入正確數字"
            );
            return -1;
        }
    }

    // 更新購物車表格
    private void refreshCart() {

        model.setRowCount(0);

        String data = cartService.getMyCart(loginUserId);

        System.out.println(data);

        // TODO: getMyCart() 目前回傳 String，無法直接拆解填入表格的 5 個欄位。
        // 建議讓它改回傳 List<Cart>（或含商品名稱/單價的 DTO），
        // 再用迴圈呼叫 model.addRow(new Object[]{ productId, productName, price, quantity, sum })
        // 才能讓 JTable 真正顯示購物車內容。
    }
}