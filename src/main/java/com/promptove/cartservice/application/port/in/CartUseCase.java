package com.promptove.cartservice.application.port.in;

import java.util.List;

//input port
public interface CartUseCase {

	void createCart(CartRequestDto cartCreateRequestDto);

	List<CartRequestDto> getCart(CartRequestDto cartGetRequestDto);

	void updateCartItem(CartRequestDto cartUpdateRequestDto);

	void deleteCartItem(CartRequestDto cartDeleteRequestDto);
}
