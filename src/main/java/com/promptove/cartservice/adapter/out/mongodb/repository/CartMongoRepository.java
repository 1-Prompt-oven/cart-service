package com.promptove.cartservice.adapter.out.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promptove.cartservice.adapter.out.mongodb.document.CartDocument;

public interface CartMongoRepository extends MongoRepository<CartDocument, Long> {

	List<CartDocument> findByMemberUuid(String memberUuid);

	void deleteByProductUuid(String cartItemUuid);

	CartDocument findByProductUuid(String cartItemUuid);
}
