package com.promptoven.cartservice.adapter.mysql.mapper;

import com.promptoven.cartservice.adapter.mysql.entity.CartEntity;
import org.springframework.stereotype.Component;

import com.promptoven.cartservice.application.port.out.dto.CartOutportDto;

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
			.build();
	}

	public CartEntity toDeleteEntity(CartOutportDto cartOutportDto) {

		return CartEntity.builder()
			.id(cartOutportDto.getId())
			.memberUuid(cartOutportDto.getMemberUuid())
			.productUuid(cartOutportDto.getProductUuid())
			.selected(cartOutportDto.isSelected())
			.deleted(cartOutportDto.isDeleted())
			.build();
	}

	public CartOutportDto toDto(CartEntity cartEntity) {
		return CartOutportDto.builder()
			.id(cartEntity.getId())
			.memberUuid(cartEntity.getMemberUuid())
			.productUuid(cartEntity.getProductUuid())
			.selected(cartEntity.isSelected())
			.deleted(cartEntity.isDeleted())
			.createdDate(cartEntity.getCreatedDate())
			.lastModifiedDate(cartEntity.getLastModifiedDate())
			.build();
	}
}
