package com.promptove.cartservice.application.port.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {
	//여러가지 컬럼이 다 들어감
	private Long id;
	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;
	private LocalDateTime createdAt;

	@Builder
	public CartRequestDto(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted, LocalDateTime createdAt) {
		this.id = id;
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}
}
