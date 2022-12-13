package com.naveejr.productservices.controller;

import com.naveejr.productservices.dto.ProductRequest;
import com.naveejr.productservices.dto.ProductResponse;
import com.naveejr.productservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
		if (productRequest.getName() == null || productRequest.getName().isEmpty())
			return ResponseEntity.badRequest().build();
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
	}


	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}


}
