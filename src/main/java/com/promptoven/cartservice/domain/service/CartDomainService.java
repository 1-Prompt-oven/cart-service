package com.promptoven.cartservice.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promptoven.cartservice.application.port.in.dto.CartInportDto;
import com.promptoven.cartservice.application.port.out.dto.CartOutportDto;
import com.promptoven.cartservice.domain.model.Cart;

@Service
public class CartDomainService {

	public Cart createCart(CartInportDto cartInportDto) {
		return Cart.builder()
			.memberUuid(cartInportDto.getMemberUuid())
			.productUuid(cartInportDto.getProductUuid())
			.selected(cartInportDto.isSelected())
			.deleted(false)
			.build();
	}

	public List<Cart> getCart(List<CartOutportDto> cartOutportDtoList) {
		return cartOutportDtoList.stream().map(cartTransactionDto -> Cart.builder()
			.id(cartTransactionDto.getId())
			.memberUuid(cartTransactionDto.getMemberUuid())
			.productUuid(cartTransactionDto.getProductUuid())
			.selected(cartTransactionDto.isSelected())
			.deleted(cartTransactionDto.isDeleted())
			.createdDate(cartTransactionDto.getCreatedDate())
			.lastModifiedDate(cartTransactionDto.getLastModifiedDate())
			.build()).toList();
	}

	public Cart updateCart(CartOutportDto cartOutportDto, CartInportDto cartInportDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartInportDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.build();
	}

	public Cart updateExistCart(CartOutportDto cartOutportDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(true)
			.deleted(false)
			.build();
	}

	public Cart deleteCart(CartOutportDto cartOutportDto) {
		return Cart.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(true)
			.build();
	}
}
