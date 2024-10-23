package com.promptove.cartservice.application.port.in;

import java.util.List;

import com.promptove.cartservice.application.port.dto.CartDto;
import com.promptove.cartservice.domain.model.Cart;

//input port
public interface CartUseCase {

	void createCart(CartDto cartDto);

	List<Cart> getCart(String memberUuid);

	void updateCartItem(String memberUuid, String productUuid, boolean isSelected);

	void deleteCartItem(String memberUuid, String productUuid);

	// 제품 삭제 시 장바구니에서 항목 삭제
	void deleteCartItemByProductUuid(String productUuid);

	// 결제 성공 후 장바구니 비우기
	void clearCartAfterOrder(String memberUuid);
}
