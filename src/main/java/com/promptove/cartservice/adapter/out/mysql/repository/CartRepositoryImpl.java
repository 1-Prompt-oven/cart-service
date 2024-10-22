package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.promptove.cartservice.adapter.in.web.dto.CartUpdateDto;
import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;
import com.promptove.cartservice.adapter.out.mysql.mapper.CartEntityMapper;
import com.promptove.cartservice.application.port.out.CartRepositoryPort;
import com.promptove.cartservice.domain.Cart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component("cartMysqlAdapter")
// output adapter
public class CartRepositoryImpl implements CartRepositoryPort {

	private final CartJpaRepository cartJpaRepository;
	private final CartEntityMapper cartEntityMapper;

	@Override
	public void save(Cart cart) {
		cartJpaRepository.save(cartEntityMapper.toEntity(cart));
	}

	@Override
	public Cart getCartByProductUuidAndMemberUuid(String productUuid, String memberUuid) {
		CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuid(productUuid, memberUuid);
		return cartEntityMapper.toDomain(cartEntity);
	}

	@Override
	public List<Cart> getCart(String memberUuid) {

		List<CartEntity> cartEntities = cartJpaRepository.findByMemberUuid(memberUuid);

		return cartEntities.stream()
			.map(cartEntityMapper::toDomain)
			.toList();  // 리스트로 변환
	}

	@Override
	public void deleteCartItem(String memberUuid, String productUuid) {
		cartJpaRepository.deleteByMemberUuidAndProductUuid(memberUuid, productUuid);
	}

	// @Override
	// public void softDeleteCartItem(String memberUuid, String productUuid) {
	// 	CartEntity cartEntity = cartJpaRepository.findByProductUuid(productUuid);
	// 	cartEntity.setDeleted(true);
	// 	cartJpaRepository.save(cartEntity);
	// }

	@Override
	public void updateCartItem(CartUpdateDto cartUpdateDto) {
		CartEntity cartEntity = cartJpaRepository.findByProductUuidAndMemberUuid(cartUpdateDto.getProductUuid(),
			cartUpdateDto.getMemberUuid());
		cartEntity.setSelected(cartUpdateDto.isSelected());
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
		List<CartEntity> cartEntities = cartJpaRepository.findByMemberUuid(memberUuid);
		for (CartEntity cartEntity : cartEntities) {
			cartEntity.setDeleted(true);  // 소프트 삭제 처리
			cartJpaRepository.save(cartEntity);
		}
	}
}
