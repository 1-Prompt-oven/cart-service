package com.promptove.cartservice.adapter.out.mysql.entity;

import java.time.LocalDateTime;

import com.promptove.cartservice.global.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CartEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;

	@Builder
	public CartEntity(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted,
		LocalDateTime createdAt) {
		this.id = id;
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
	}
}
