package com.promptoven.cartservice.application.port.call;

import com.promptoven.cartservice.application.port.dto.in.CartRequestDto;

import java.util.List;

//input port
public interface CartUseCase {

    void createCart(CartRequestDto cartCreateRequestDto);

    List<CartRequestDto> getCart(CartRequestDto cartGetRequestDto);

    void updateCartItem(CartRequestDto cartUpdateRequestDto);

    void deleteCartItem(CartRequestDto cartDeleteRequestDto);
}
