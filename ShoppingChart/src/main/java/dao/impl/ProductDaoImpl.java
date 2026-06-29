package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.ProductDao;
import model.Product;
import util.DbConnection;

public class ProductDaoImpl implements ProductDao {

    Connection conn = DbConnection.getDb();

    @Override
    public void insert(Product product) {

        String sql =
                "INSERT INTO product(product_name,product_amount,product_price) " +
                "VALUES(?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductAmount());
            ps.setInt(3, product.getProductPrice());

            ps.executeUpdate();

            // 把資料庫自動產生的 product_id 寫回 product 物件，
            // 這樣呼叫端（Service／UI）才能拿到新商品真正的 ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setProductId(rs.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String selectAll() {

        String sql = "SELECT * FROM product";
        String showAll = "";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                showAll = showAll
                        + "商品編號:" + rs.getInt("product_id")
                        + "\t商品名稱：" + rs.getString("product_name")
                        + "\t庫存：" + rs.getString("product_amount")
                        + "\t價格：" + rs.getString("product_price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showAll;
    }

    @Override
    public Product selectById(int productId) {

        Product product = null;

        String sql =
                "SELECT * FROM product WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductAmount(rs.getInt("product_amount"));
                product.setProductPrice(rs.getInt("product_price"));
                product.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product selectByName(String productName) {

        Product product = null;

        String sql =
                "SELECT * FROM product WHERE product_name=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, productName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductAmount(rs.getInt("product_amount"));
                product.setProductPrice(rs.getInt("product_price"));
                product.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product selectByPrice(int productPrice) {

        Product product = null;

        String sql =
                "SELECT * FROM product WHERE product_price=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productPrice);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product = new Product();

                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductAmount(rs.getInt("product_amount"));
                product.setProductPrice(rs.getInt("product_price"));
                product.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void update(Product product) {

        String sql =
                "UPDATE product " +
                "SET product_name=?,product_amount=?,product_price=? " +
                "WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getProductAmount());
            ps.setInt(3, product.getProductPrice());
            ps.setInt(4, product.getProductId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int productId) {

        String sql =
                "DELETE FROM product WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}