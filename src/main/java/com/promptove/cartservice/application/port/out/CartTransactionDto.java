package com.promptove.cartservice.application.port.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CartTransactionDto {

	//여러가지 컬럼이 다 들어감
	private Long id;
	private String memberUuid;
	private String productUuid;
	private boolean selected;
	private boolean deleted;
	private LocalDateTime createdAt;

	@Builder
	public CartTransactionDto(Long id, String memberUuid, String productUuid, boolean selected, boolean deleted,
		LocalDateTime createdAt) {
		this.id = id;
		this.memberUuid = memberUuid;
		this.productUuid = productUuid;
		this.selected = selected;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}
}
