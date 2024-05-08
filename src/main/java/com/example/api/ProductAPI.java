package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.ProductDTO;
import com.example.exeption.ErrorCode;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/product"})
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @PostMapping
    public ApiResponse<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO result = productService.save(productDTO);
        return ApiResponse.<ProductDTO>builder()
                .code(ErrorCode.PRODUCT_CREATE_SUCCESSFULLY.getCode())
                .message(ErrorCode.PRODUCT_CREATE_SUCCESSFULLY.getMessage())
                .result(result)
                .build();
    }

    @PutMapping(value = "/{id}")
    public ApiResponse<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
        ProductDTO result = productService.update(productDTO, id);
        return ApiResponse.<ProductDTO>builder()
                .code(ErrorCode.PRODUCT_UPDATE_SUCCESSFULLY.getCode())
                .message(ErrorCode.PRODUCT_UPDATE_SUCCESSFULLY.getMessage())
                .result(result)
                .build();
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<ProductDTO> findProductById(@PathVariable("id") Long id) {
        ProductDTO result = productService.findProductById(id);
        return ApiResponse.<ProductDTO>builder()
                .result(result)
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductDTO>> findAll() {
        List<ProductDTO> result = productService.findAll();
        return ApiResponse.<List<ProductDTO>>builder()
                .result(result)
                .build();
    }

    //delete {role: ADMIN, MANAGER}

}