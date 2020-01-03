ackage com.niit.dao;

import java.util.List;

import com.niit.model.Cart;
import com.niit.model.Product;

public interface CartDAO {

	public boolean addToCart(Cart cart);
	public boolean deleteItemFromCart(Cart cart);
	public boolean updateItemFromCart(Cart cart);
	public Cart getCart(int cartItemId);
	public List<Cart> listCartItem(String username);
}