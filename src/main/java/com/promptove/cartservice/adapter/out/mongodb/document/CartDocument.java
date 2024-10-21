package com.promptove.cartservice.adapter.out.mongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cart")
public class CartDocument {

	@Id
	private String id;

	private Long cartId;
	private String memberUuid;
	private String productUuid;
	private String promptThumbnail;
	private String modelName;
	private String promptName;
	private int price;
	private boolean selected;
}
