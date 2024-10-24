package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.adapter.out.mysql.mapper.CartEntityMapper;
import com.promptove.cartservice.application.mapper.CartDtoMapper;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;
import com.promptove.cartservice.application.port.out.CartTransactionDto;
import com.promptove.cartservice.domain.model.Cart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
@Component("cartMysqlAdapter")
// output adapter
public class CartRepositoryImpl implements CartRepositoryPort {

	private final CartJpaRepository cartJpaRepository;
	private final CartEntityMapper cartEntityMapper;
	private final CartDtoMapper cartDtoMapper;

	@Override
	public Optional<CartTransactionDto> getCartByProductUuidAndMemberUuid(String productUuid, String memberUuid) {

		return cartJpaRepository.findByProductUuidAndMemberUuid(productUuid, memberUuid).map(cartEntityMapper::toDto);
	}

	@Override
	public void save(CartTransactionDto cartTransactionDto) {

		cartJpaRepository.save(cartEntityMapper.toEntity(cartTransactionDto));
	}

	@Override
	public List<Cart> getCart(CartTransactionDto cartTransactionDto) {

		List<CartEntity> cartEntities = cartJpaRepository.findByMemberUuidAndDeletedFalse(
			cartTransactionDto.getMemberUuid());

		return cartEntities.stream()
			.map(cartEntityMapper::EntityToDomain)
			.toList();  // 리스트로 변환
	}

	@Override
	public void updateCartItem(CartTransactionDto cartTransactionDto) {

		cartJpaRepository.save(cartEntityMapper.toUpdateEntity(cartTransactionDto));
	}

	@Override
	public void deleteCartItem(CartTransactionDto cartTransactionDto) {

		cartJpaRepository.save(cartEntityMapper.toDeleteEntity(cartTransactionDto));
	}
}
