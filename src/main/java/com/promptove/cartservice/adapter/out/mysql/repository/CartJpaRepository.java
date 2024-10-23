package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity> findByProductUuidAndMemberUuidAndDeleted(String productUuid, String memberUuid,
		boolean deleted);

	Optional<CartEntity> findByProductUuidAndMemberUuid(String productUuid, String memberUuid);

	List<CartEntity> findByMemberUuidAndDeletedFalse(String memberUuid);

	List<CartEntity> findByProductUuid(String productUuid);
}
