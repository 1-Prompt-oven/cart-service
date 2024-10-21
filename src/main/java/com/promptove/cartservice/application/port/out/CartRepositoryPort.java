package com.promptove.cartservice.application.port.out;

import java.util.List;

import com.promptove.cartservice.adapter.in.web.dto.CartUpdateDto;
import com.promptove.cartservice.domain.Cart;

//output port
public interface CartRepositoryPort {

	void save(Cart cart);

	List<Cart> getCart(String memberUuid);

	void deleteCartItem(String memberUuid, String productUuid);
	// void softDeleteCartItem(String memberUuid, String productUuid);

	void updateCartItem(CartUpdateDto cartUpdateDto);

	void deleteCartItemsByProductUuid(String productUuid);  // 제품 삭제 시 관련 장바구니 항목 삭제

	void clearCartByMemberUuid(String memberUuid);  // 결제 성공 시 장바구니 비우기
}
