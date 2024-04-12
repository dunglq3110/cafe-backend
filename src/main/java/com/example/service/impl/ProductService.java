package com.example.service.impl;

import com.example.dto.ProductDTO;
import com.example.dto.SizeDTO;
import com.example.entity.Product;
import com.example.entity.ProductSize;
import com.example.entity.Size;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import com.example.repository.ProductSizeRepository;
import com.example.repository.SizeRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        // Assuming productSizes is a list of SizeDTOs in your ProductDTO
        if (productDTO.getSizes() != null) {
            for (SizeDTO sizeDTO : productDTO.getSizes()) {
                ProductSize productSize = new ProductSize();
                productSize.setProduct(product);
                Optional<Size> size = sizeRepository.findByName(sizeDTO.getName());
                if (size.isPresent()){
                    productSize.setSize(size.get());
                    productSize.setPrice(sizeDTO.getPrice());
                    productSize = productSizeRepository.save(productSize);
                    product.getProductSizes().add(productSize);
                }
            }
        }
        ProductDTO result = productMapper.toDTO(product);
        return result;
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO result = null;
        if (product.isPresent())
        {
            result = productMapper.toDTO(product.get());
        }
        return result;
    }
    @Override
    public List<ProductDTO> findAll() {
        return productMapper.toDTOs(productRepository.findAll());
    }


}
