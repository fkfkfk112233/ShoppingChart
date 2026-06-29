package service.impl;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import model.Cart;
import service.CartService;

public class CartServiceImpl implements CartService{

    CartDao cdi=new CartDaoImpl();

    @Override
    public Cart addCart(Cart cart) {

        if(cdi.selectByProductId(cart.getProductId())==null) {

            cdi.insert(cart);

            return cart;
        }

        return null;
    }

    @Override
    public String getMyCart(int userId) {

        return cdi.selectByUserId(userId);
    }

    @Override
    public Cart updateCart(Cart cart) {

        cdi.update(cart);

        return cart;
    }

    @Override
    public void removeProduct(int userId,int productId) {

        cdi.delete(productId);

    }

    @Override
    public void clearCart(int userId) {

        cdi.deleteByUserId(userId);

    }

}