package com.promptove.cartservice.adapter.out.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promptove.cartservice.adapter.out.mysql.entity.CartEntity;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {

	CartEntity findByProductUuidAndMemberUuid(String productUuid, String memberUuid);

	List<CartEntity> findByMemberUuid(String memberUuid);

	void deleteByMemberUuidAndProductUuid(String memberUuid, String productUuid);

	List<CartEntity> findByProductUuid(String productUuid);
}
