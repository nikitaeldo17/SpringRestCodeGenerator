package com.example.SpringRestCodeGenerator.controller.impl;

import com.example.SpringRestCodeGenerator.controller.ProductController;
import com.example.SpringRestCodeGenerator.dto.ProductDTO;
import com.example.SpringRestCodeGenerator.entity.Product;
import com.example.SpringRestCodeGenerator.mapper.ProductMapper;
import com.example.SpringRestCodeGenerator.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/product")
@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductControllerImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.asEntity(productDTO);
        return productMapper.asDTO(productService.save(product));
    }

    @Override
    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable("id") int id) {
        Product product = productService.findById(id).orElse(null);
        return productMapper.asDTO(product);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<ProductDTO> list() {
        return productMapper.asDTOList(productService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<ProductDTO> pageQuery(Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);
        List<ProductDTO> dtoList = productPage
                .stream()
                .map(productMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, productPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable("id") int id) {
        Product product = productMapper.asEntity(productDTO);
        return productMapper.asDTO(productService.update(product, id));
    }
}