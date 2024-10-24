package com.promptove.cartservice.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.application.port.in.CartRequestDto;
import com.promptove.cartservice.application.port.out.CartOutportDto;
import com.promptove.cartservice.domain.model.Cart;

@Component
public class CartDtoMapper {

	public Cart toDomain(CartRequestDto cartRequestDto) {
		return Cart.builder()
			.memberUuid(cartRequestDto.getMemberUuid())
			.productUuid(cartRequestDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(cartRequestDto.isDeleted())
			.createdAt(cartRequestDto.getCreatedAt())
			.build();
	}

	public CartOutportDto toDto(Cart cart) {
		return CartOutportDto.builder()
			.id(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build();
	}

	public List<CartRequestDto> toDtoList(List<Cart> cartList) {
		return cartList.stream().map(cart -> CartRequestDto.builder()
			.id(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdAt(cart.getCreatedAt())
			.build()).toList();
	}
}
