package com.promptove.cartservice.application.port.in;

//input port
public interface CartUseCase {

	void createCart(CartRequestDto cartCreateRequestDto);

	// List<Cart> getCart(CartRequestDto cartRequestDto);

	void updateCartItem(CartRequestDto cartUpdateRequestDto);

	void deleteCartItem(CartRequestDto cartDeleteRequestDto);
}
