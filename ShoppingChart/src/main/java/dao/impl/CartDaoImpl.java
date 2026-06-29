package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.CartDao;
import model.Cart;
import util.DbConnection;

public class CartDaoImpl implements CartDao {

    Connection conn = DbConnection.getDb();

    @Override
    public void insert(Cart cart) {

        String sql =
                "INSERT INTO cart(user_id,product_id,quantity,sum) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cart.getUserId());
            ps.setInt(2, cart.getProductId());
            ps.setInt(3, cart.getQuantity());
            ps.setInt(4, cart.getSum());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String selectAll() {

        StringBuilder sb = new StringBuilder();

        String sql = "SELECT * FROM cart";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                sb.append("會員ID：")
                  .append(rs.getInt("user_id"))
                  .append("\t商品ID：")
                  .append(rs.getInt("product_id"))
                  .append("\t數量：")
                  .append(rs.getInt("quantity"))
                  .append("\t總金額：")
                  .append(rs.getInt("sum"))
                  .append("\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    public String selectByUserId(int userId) {

        StringBuilder sb = new StringBuilder();

        String sql =
                "SELECT * FROM cart WHERE user_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                sb.append("商品ID：")
                  .append(rs.getInt("product_id"))
                  .append("\t數量：")
                  .append(rs.getInt("quantity"))
                  .append("\t總金額：")
                  .append(rs.getInt("sum"))
                  .append("\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

    @Override
    public Cart selectByProductId(int productId) {

        Cart cart = null;

        String sql =
                "SELECT * FROM cart WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                cart = new Cart();

                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setSum(rs.getInt("sum"));
                cart.setCreateAt(
                        rs.getTimestamp("create_at").toLocalDateTime());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public void update(Cart cart) {

        String sql =
                "UPDATE cart SET quantity=?, sum=? WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cart.getQuantity());
            ps.setInt(2, cart.getSum());
            ps.setInt(3, cart.getProductId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int productId) {

        String sql =
                "DELETE FROM cart WHERE product_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByUserId(int userId) {

        String sql =
                "DELETE FROM cart WHERE user_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
