package com.promptove.cartservice.application.port.out;

import java.util.List;
import java.util.Optional;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.domain.model.Cart;

//output port
public interface CartRepositoryPort {

	Optional<CartTransactionDto> getCartByProductUuidAndMemberUuid(String productUuid, String memberUuid);

	void save(CartTransactionDto cartTransactionDto);

	List<Cart> getCart(CartTransactionDto cartTransactionDto);

	void updateCartItem(CartTransactionDto cartTransactionDto);

	// void deleteCartItem(String memberUuid, String productUuid);
}
