package com.promptove.cartservice.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.in.web.vo.CartDeleteRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartRequestVo;
import com.promptove.cartservice.adapter.in.web.vo.CartResponseVo;
import com.promptove.cartservice.adapter.in.web.vo.CartUpdateVo;
import com.promptove.cartservice.application.port.in.CartRequestDto;
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

	public CartRequestDto toUpdateDto(CartUpdateVo cartUpdateVo) {
		return CartRequestDto.builder()
			.memberUuid(cartUpdateVo.getMemberUuid())
			.productUuid(cartUpdateVo.getProductUuid())
			.selected(cartUpdateVo.isSelected())
			.build();
	}

	public CartRequestDto toDeleteDto(CartDeleteRequestVo cartDeleteRequestVo) {
		return CartRequestDto.builder()
			.memberUuid(cartDeleteRequestVo.getMemberUuid())
			.productUuid(cartDeleteRequestVo.getProductUuid())
			.build();
	}

	public CartResponseVo toResponseVo(Cart cart) {
		return CartResponseVo.builder()
			.cartId(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}
}
