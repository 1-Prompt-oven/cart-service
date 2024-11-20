package com.promptoven.cartservice.adapter.mysql.repository;

import java.util.List;
import java.util.Optional;

import com.promptoven.cartservice.adapter.mysql.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity> findByProductUuidAndMemberUuid(String productUuid, String memberUuid);

	List<CartEntity> findByMemberUuidAndDeletedFalse(String memberUuid);
}
