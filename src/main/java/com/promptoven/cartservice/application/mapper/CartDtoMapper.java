package com.promptoven.cartservice.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.promptoven.cartservice.application.port.in.dto.CartInportDto;
import com.promptoven.cartservice.application.port.out.dto.CartOutportDto;
import com.promptoven.cartservice.domain.model.Cart;

@Component
public class CartDtoMapper {

	public Cart toDomain(CartInportDto cartInportDto) {
		return Cart.builder()
			.memberUuid(cartInportDto.getMemberUuid())
			.productUuid(cartInportDto.getProductUuid())
			.selected(cartInportDto.isSelected())
			.deleted(cartInportDto.isDeleted())
			.createdDate(cartInportDto.getCreatedDate())
			.lastModifiedDate(cartInportDto.getLastModifiedDate())
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

	public List<CartInportDto> toDtoList(List<Cart> cartList) {
		return cartList.stream().map(cart -> CartInportDto.builder()
			.cartId(cart.getId())
			.memberUuid(cart.getMemberUuid())
			.productUuid(cart.getProductUuid())
			.selected(cart.isSelected())
			.deleted(cart.isDeleted())
			.createdDate(cart.getCreatedDate())
			.lastModifiedDate(cart.getLastModifiedDate())
			.build()).toList();
	}
}
