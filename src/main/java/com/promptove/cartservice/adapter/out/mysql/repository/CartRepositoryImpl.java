package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.adapter.out.mysql.mapper.CartEntityMapper;
import com.promptove.cartservice.application.mapper.CartDtoMapper;
import com.promptove.cartservice.application.port.in.CartRequestDto;
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
	public Optional<CartRequestDto> getCartByProductUuidAndMemberUuid(Cart cart) {
		// boolean deleted = true;
		// CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuidAndDeleted(productUuid, memberUuid,
		// 	deleted).orElse(null);

		CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuid(cart.getProductUuid(),
				cart.getMemberUuid())
			.orElse(null);

		return Optional.ofNullable(cartEntityMapper.EntityToDto(cartEntity));
	}

	@Override
	public void save(CartTransactionDto cartTransactionDto) {
		cartJpaRepository.save(cartEntityMapper.toEntity(cartTransactionDto));
	}

	@Override
	public List<Cart> getCart(String memberUuid) {

		List<CartEntity> cartEntities = cartJpaRepository.findByMemberUuidAndDeletedFalse(memberUuid);

		return cartEntities.stream()
			.map(cartEntityMapper::EntityToDomain)
			.toList();  // 리스트로 변환
	}

	@Override
	public void updateCartItem(Cart cart) {
		CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuid(cart.getProductUuid(),
			cart.getMemberUuid()).orElseThrow(() -> new IllegalArgumentException("해당 제품이 장바구니에 없습니다."));
		cartEntity.setSelected(cart.isSelected());
		cartJpaRepository.save(cartEntity);
	}

	@Override
	public void deleteCartItem(String memberUuid, String productUuid) {
		CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuid(productUuid, memberUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 제품이 장바구니에 없습니다."));
		cartEntity.setDeleted(true);
		cartJpaRepository.save(cartEntity);
	}

	@Override
	public void deleteCartItemsByProductUuid(String productUuid) {
		List<CartEntity> cartEntities = cartJpaRepository.findByProductUuid(productUuid);
		for (CartEntity cartEntity : cartEntities) {
			cartEntity.setDeleted(true);  // 소프트 삭제 처리
			cartJpaRepository.save(cartEntity);
		}
	}

	@Override
	public void clearCartByMemberUuid(String memberUuid) {
		List<CartEntity> cartEntities = cartJpaRepository.findByMemberUuidAndDeletedFalse(memberUuid);
		for (CartEntity cartEntity : cartEntities) {
			cartEntity.setDeleted(true);  // 소프트 삭제 처리
			cartJpaRepository.save(cartEntity);
		}
	}
}
