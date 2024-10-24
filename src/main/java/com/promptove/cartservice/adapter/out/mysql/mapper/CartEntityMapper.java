package com.promptove.cartservice.adapter.out.mysql.mapper;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.application.port.out.CartOutportDto;
import com.promptove.cartservice.domain.model.Cart;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CartEntityMapper {

	public CartEntity toEntity(CartOutportDto cartOutportDto) {
		return CartEntity.builder()
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.createdAt(cartOutportDto.getCreatedAt())
			.build();
	}

	public CartEntity toUpdateEntity(CartOutportDto cartOutportDto) {

		log.info("cartTransactionDto: {}", cartOutportDto.toString());

		return CartEntity.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.createdAt(cartOutportDto.getCreatedAt())
			.build();
	}

	public CartEntity toDeleteEntity(CartOutportDto cartOutportDto) {

		return CartEntity.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.createdAt(cartOutportDto.getCreatedAt())
			.build();
	}

	public CartOutportDto toDto(CartEntity cartEntity) {
		return CartOutportDto.builder()
			.id(cartEntity.getId())
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
			.build();
	}

	public Cart EntityToDomain(CartEntity cartEntity) {
		return Cart.builder()
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdAt(cartEntity.getCreatedAt())
			.build();
	}
}
