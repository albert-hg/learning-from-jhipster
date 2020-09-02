package com.albert.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.albert.management.dto.ProductDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<Long, ProductDTO> products = new HashMap<>();

    public ProductController() {
        this.products.put(1L, new ProductDTO(1L, "apple", 10));
        this.products.put(2L, new ProductDTO(2L, "book", 20));
        this.products.put(3L, new ProductDTO(3L, "car", 30));
    }

    @ApiOperation(value="取得物品資訊", notes="透過物品的 id 取得該物品的相關資訊")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="成功取得物品"),
        @ApiResponse(responseCode="404", description="找不到物品")
    })
    @GetMapping(value="/{id}", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> getBook(
        @ApiParam("物品id") @PathVariable Long id
    ) {
        ProductDTO _product = this.products.get(id);
        if (_product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(_product);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ArrayList<ProductDTO>> getBook() {
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<ProductDTO>(this.products.values()));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createBook(@RequestBody ProductDTO product) {
        if (this.products.containsKey(product.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        } else {
            this.products.putIfAbsent(product.getId(), product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateBook(@RequestBody ProductDTO product) {
        if (!this.products.containsKey(product.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(product);
        } else {
            ProductDTO _product = this.products.get(product.getId());
            _product.setName(product.getName());
            _product.setRemain(product.getRemain());
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        this.products.remove(id);
        return ResponseEntity.ok().build();
    }
}
