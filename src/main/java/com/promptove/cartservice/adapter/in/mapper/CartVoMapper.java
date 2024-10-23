package com.promptove.cartservice.adapter.in.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.application.port.dto.CartDto;
import com.promptove.cartservice.application.port.dto.CartUpdateDto;
import com.promptove.cartservice.adapter.in.rest.vo.CartRequestVo;
import com.promptove.cartservice.adapter.in.rest.vo.CartResponseVo;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartVoMapper {

	public CartDto toDto(CartRequestVo cartRequestVo) {
		return CartDto.builder()
			.memberUuid(cartRequestVo.getMemberUuid())
			.productUuid(cartRequestVo.getProductUuid())
			.selected(true)
			.deleted(false)
			.createdAt(cartRequestVo.getCreatedAt())
			.build();
	}

	public CartUpdateDto toDto(CartUpdateDto cartUpdateVo) {
		return CartUpdateDto.builder()
			.memberUuid(cartUpdateVo.getMemberUuid())
			.productUuid(cartUpdateVo.getProductUuid())
			.selected(cartUpdateVo.isSelected())
			.build();
	}

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
