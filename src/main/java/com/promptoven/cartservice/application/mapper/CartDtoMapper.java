package com.promptoven.cartservice.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.promptoven.cartservice.application.port.dto.in.CartRequestDto;
import com.promptoven.cartservice.application.port.dto.out.CartOutportDto;
import com.promptoven.cartservice.domain.model.Cart;

@Component
public class CartDtoMapper {

	public Cart toDomain(CartRequestDto cartRequestDto) {
		return Cart.builder()
			.memberUuid(cartRequestDto.getMemberUuid())
			.productUuid(cartRequestDto.getProductUuid())
			.selected(cartRequestDto.isSelected())
			.deleted(cartRequestDto.isDeleted())
			.createdDate(cartRequestDto.getCreatedDate())
			.lastModifiedDate(cartRequestDto.getLastModifiedDate())
			.build();
	}

	public CartOutportDto toCreateDto(Cart cart) {
		return CartOutportDto.builder()
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.build();
	}

	public CartOutportDto toDto(Cart cart) {
		return CartOutportDto.builder()
			.id(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdDate(cart.getCreatedDate())
			.build();
	}

	public List<CartRequestDto> toDtoList(List<Cart> cartList) {
		return cartList.stream().map(cart -> CartRequestDto.builder()
			.id(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdDate(cart.getCreatedDate())
			.lastModifiedDate(cart.getLastModifiedDate())
			.build()).toList();
	}
}
