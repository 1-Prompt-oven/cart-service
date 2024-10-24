package com.promptove.cartservice.application.port.in;

import java.util.List;

import com.promptove.cartservice.domain.model.Cart;

//input port
public interface CartUseCase {

	void createCart(CartRequestDto cartCreateRequestDto);

	// List<Cart> getCart(CartRequestDto cartRequestDto);

	void updateCartItem(CartRequestDto cartUpdateRequestDto);

	// void deleteCartItem(String memberUuid, String productUuid);
}
