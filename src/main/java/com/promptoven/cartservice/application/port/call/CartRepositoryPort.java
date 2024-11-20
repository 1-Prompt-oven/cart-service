package com.promptoven.cartservice.application.port.call;

import com.promptoven.cartservice.application.port.dto.out.CartOutportDto;

import java.util.List;
import java.util.Optional;

//output port
public interface CartRepositoryPort {

    Optional<CartOutportDto> getCartByProductUuidAndMemberUuid(String productUuid, String memberUuid);

    Optional<CartOutportDto> getCartByCartId(Long id);

    void save(CartOutportDto cartOutportDto);

    List<CartOutportDto> getCart(CartOutportDto cartOutportDto);

    void updateCartItem(CartOutportDto cartOutportDto);

    void deleteCartItem(CartOutportDto cartOutportDto);
}