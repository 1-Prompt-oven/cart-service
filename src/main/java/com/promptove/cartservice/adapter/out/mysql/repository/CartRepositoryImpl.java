package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.promptove.cartservice.adapter.out.mysql.mapper.CartEntityMapper;
import com.promptove.cartservice.application.port.out.CartOutportDto;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@Component("cartMysqlAdapter")
// output adapter
public class CartRepositoryImpl implements CartRepositoryPort {

	private final CartJpaRepository cartJpaRepository;
	private final CartEntityMapper cartEntityMapper;

	@Override
	public Optional<CartOutportDto> getCartByProductUuidAndMemberUuid(String productUuid, String memberUuid) {

		return cartJpaRepository.findByProductUuidAndMemberUuid(productUuid, memberUuid).map(cartEntityMapper::toDto);
	}

	@Override
	public void save(CartOutportDto cartOutportDto) {

		cartJpaRepository.save(cartEntityMapper.toEntity(cartOutportDto));
	}

	@Override
	public List<CartOutportDto> getCart(CartOutportDto cartOutportDto) {

		return cartJpaRepository.findByMemberUuidAndDeletedFalse(cartOutportDto.getMemberUuid()).stream()
			.map(cartEntityMapper::toDto).toList();
	}

	@Override
	public void updateCartItem(CartOutportDto cartOutportDto) {

		cartJpaRepository.save(cartEntityMapper.toUpdateEntity(cartOutportDto));
	}

	@Override
	public void deleteCartItem(CartOutportDto cartOutportDto) {

		cartJpaRepository.save(cartEntityMapper.toDeleteEntity(cartOutportDto));
	}
}
