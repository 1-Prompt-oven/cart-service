package com.promptove.cartservice.adapter.in.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.in.web.vo.CartResponseVo;
import com.promptove.cartservice.domain.Cart;

@Component
public class CartVoMapper {

	public CartResponseVo toResponseVo(Cart cart) {
		return CartResponseVo.builder()
			.cartId(cart.getCartId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}
}
