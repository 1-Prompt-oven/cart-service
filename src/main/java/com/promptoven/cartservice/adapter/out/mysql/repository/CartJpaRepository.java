package com.promptoven.cartservice.adapter.out.mysql.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptoven.cartservice.adapter.out.mysql.entity.CartEntity;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity> findByProductUuidAndMemberUuid(String productUuid, String memberUuid);

	List<CartEntity> findByMemberUuidAndDeletedFalse(String memberUuid);
}
