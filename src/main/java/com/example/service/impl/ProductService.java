package com.example.service.impl;

import com.example.dto.ProductDTO;
import com.example.dto.SizeDTO;
import com.example.dto.cart.ProductRequest;
import com.example.dto.cart.ProductResponse;
import com.example.entity.Product;
import com.example.entity.ProductSize;
import com.example.entity.Size;
import com.example.exeption.AppException;
import com.example.exeption.ErrorCode;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import com.example.repository.ProductSizeRepository;
import com.example.repository.SizeRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            product.setProductSizes(new ArrayList<>());
            for (SizeDTO sizeDTO : productDTO.getSizes()) {
                ProductSize productSize = new ProductSize();
                productSize.setProduct(product);
                Optional<Size> size = sizeRepository.findByName(sizeDTO.getName());
                if (size.isPresent()){
                    productSize.setName(size.get().getName());
                    productSize.setSize(size.get());
                    productSize.setPrice(sizeDTO.getPrice());
                    productSize = productSizeRepository.save(productSize);
                    product.getProductSizes().add(productSize);
                }
            }
        }
        product = productRepository.save(product);
        ProductDTO result = productMapper.toDTO(product);
        return result;
    }

    //update product
    @Override
    public ProductDTO update(ProductDTO productDTO, long id) {
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setImage(productDTO.getImage());
        product.setDiscount(productDTO.getDiscount());
        product.setProductType(productDTO.getProductType());
        product.setProductStatus(productDTO.getProductStatus());

        // Assuming productSizes is a list of SizeDTOs in your ProductDTO
        if (productDTO.getSizes() != null) {
            for (SizeDTO sizeDTO : productDTO.getSizes()) {
                Optional<ProductSize> existingProductSize = product.getProductSizes().stream()
                        .filter(ps -> ps.getSize().getName().equals(sizeDTO.getName()))
                        .findFirst();

                if (existingProductSize.isPresent()) {
                    // Update the existing ProductSize
                    ProductSize productSize = existingProductSize.get();
                    productSize.setPrice(sizeDTO.getPrice());
                    productSize = productSizeRepository.save(productSize);
                } else {
                    // Create a new ProductSize
                    ProductSize productSize = new ProductSize();
                    productSize.setProduct(product);
                    Optional<Size> size = sizeRepository.findByName(sizeDTO.getName());
                    if (size.isPresent()){
                        productSize.setName(size.get().getName());
                        productSize.setSize(size.get());
                        productSize.setPrice(sizeDTO.getPrice());
                        productSize = productSizeRepository.save(productSize);
                        product.getProductSizes().add(productSize);
                    }
                }
            }
        }

        product = productRepository.save(product);
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
    public SizeDTO findSizeById(Long id) {
        Optional<Size> size = sizeRepository.findById(id);
        SizeDTO result = null;
        if (size.isPresent())
        {
            result = new SizeDTO();
            result.setId(size.get().getId());
            result.setName(size.get().getName());
        }
        return result;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productMapper.toDTOs(productRepository.findAll());
    }

    @Override
    public ProductResponse findById(int id) {
        Optional<Product> productOptional = productRepository.findById((long) id);

        if (!productOptional.isPresent()) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        Product product = productOptional.get();
        ProductResponse newProduct = new ProductResponse();
        newProduct.setName(product.getName());
        newProduct.setDiscount(product.getDiscount());
        newProduct.setProductStatus(product.getProductStatus());
        newProduct.setProductType(product.getProductType());

        return newProduct;
    }

    @Override
    public SizeDTO checkProductSize(ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById((long) productRequest.getId());
        if (!productOptional.isPresent()){
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Product product = productOptional.get();
        List<ProductSize> productSizes = product.getProductSizes();

        String sizeName = switch (productRequest.getSize()) {
            case 1 -> "S";
            case 2 -> "M";
            case 3 -> "L";
            default -> throw new AppException(ErrorCode.SIZE_NOT_FOUND);
        };

        Optional<ProductSize> productSizeOptional = productSizes.stream()
                .filter(ps -> ps.getSize().getName().equals(sizeName))
                .findFirst();

        if (!productSizeOptional.isPresent()) {
            throw new AppException(ErrorCode.SIZE_NOT_FOUND);
        }

        ProductSize productSize = productSizeOptional.get();
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setId(productSize.getId());
        sizeDTO.setName(productSize.getName());
        sizeDTO.setPrice(productSize.getPrice());

        return sizeDTO;
    }
}
