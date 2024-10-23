package com.promptove.cartservice.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.in.web.vo.CartRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartResponseVo;
import com.promptove.cartservice.adapter.in.web.vo.CartUpdateVo;
import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.dto.CartUpdateDto;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartVoMapper {

	public CartRequestDto toDto(CartRequestVo cartRequestVo) {
		return CartRequestDto.builder()
			.memberUuid(cartRequestVo.getMemberUuid())
			.productUuid(cartRequestVo.getProductUuid())
			.selected(true)
			.deleted(false)
			.createdAt(cartRequestVo.getCreatedAt())
			.build();
	}

	public CartUpdateDto toDto(CartUpdateVo cartUpdateVo) {
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
