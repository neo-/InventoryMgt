package com.naveejr.productservices.service;

import com.naveejr.productservices.dto.ProductRequest;
import com.naveejr.productservices.dto.ProductResponse;
import com.naveejr.productservices.model.Product;
import com.naveejr.productservices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	private final ProductRepository productRepository;

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).build();
	}

	private Product mapToProduct(ProductRequest product) {
		return Product.builder()
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).build();
	}

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = mapToProduct(productRequest);
		productRepository.save(product);
		log.info("Product is saved {}", product.getId());
		return mapToProductResponse(product);

	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll().stream().map(this::mapToProductResponse).collect(Collectors.toList());
	}
}
