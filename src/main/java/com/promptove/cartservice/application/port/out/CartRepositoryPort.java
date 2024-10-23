package com.promptove.cartservice.application.port.out;

import java.util.List;
import java.util.Optional;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.domain.model.Cart;

//output port
public interface CartRepositoryPort {

	Optional<CartRequestDto> getCartByProductUuidAndMemberUuid(Cart cart);

	void save(CartTransactionDto cartTransactionDto);

	List<Cart> getCart(String memberUuid);

	void updateCartItem(Cart cart);

	void deleteCartItem(String memberUuid, String productUuid);

	void deleteCartItemsByProductUuid(String productUuid);  // 제품 삭제 시 관련 장바구니 항목 삭제

	void clearCartByMemberUuid(String memberUuid);  // 결제 성공 시 장바구니 비우기
}
