package com.promptoven.cartservice.application.port.in.usecase;

import com.promptoven.cartservice.application.port.in.dto.CartInportDto;

import java.util.List;

//input port
public interface CartUseCase {

    void createCart(CartInportDto cartCreateRequestDto);

    List<CartInportDto> getCart(CartInportDto cartGetRequestDto);

    void updateCartItem(CartInportDto cartUpdateRequestDto);

    void deleteCartItem(CartInportDto cartDeleteRequestDto);

    Boolean isCartExist(CartInportDto cartInportDto);
}
