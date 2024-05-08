package com.example.mapper;

import com.example.dto.ProductDetailDTO;
import com.example.dto.cart.OrderResponse;
import com.example.entity.ProductCondimentDetail;
import com.example.entity.ProductDetail;
import com.example.entity.ProductSize;
import com.example.repository.ProductCondimentDetailRepository;
import com.example.repository.ProductDetailRepository;
import com.example.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductDetailMapper {
    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ProductCondimentDetailMapper productCondimentDetailMapper;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductCondimentDetailRepository productCondimentDetailRepository;

    public List<ProductDetailDTO> mapOrderResponseToProductDetailDTO(List<OrderResponse> orderResponses) {
        int count = productDetailRepository.findAll().size();
        List<ProductDetailDTO> productDetailDTOs = new ArrayList<>();
        for (int i = 0; i < orderResponses.size(); i++) {
            OrderResponse orderResponse = orderResponses.get(i);
            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setId(count + i + 1L);
            productDetailDTO.setProductId(orderResponse.getProduct().getId());
            productDetailDTO.setProductQuantity(orderResponse.getQuantity());
            productDetailDTO.setProductDiscount(orderResponse.getProduct().getDiscount());
            productDetailDTO.setProductPrice(orderResponse.getProduct().getSize().getPrice());
            productDetailDTO.setProductSizeId(orderResponse.getProduct().getSize().getId());
            productDetailDTO.setProductCondimentDetails(productCondimentDetailMapper
                    .mapCondimentDetailsResponseToProductCondimentDetailDTO(orderResponse.getProductCondimentDetails(), count + i + 1L));
            productDetailDTOs.add(productDetailDTO);
        }
        return productDetailDTOs;
    }

    public ProductDetail toEntity(ProductDetailDTO productDetailDTO) {
        ProductDetail productDetail;

        productDetail = productDetailRepository.findById(productDetailDTO.getId())
                .orElse(new ProductDetail());
        // Check if id is not null before fetching the ProductDetail

        productDetail.setProductQuantity(productDetailDTO.getProductQuantity());
        productDetail.setProductDiscount(productDetailDTO.getProductDiscount());
        productDetail.setProductPrice(productDetailDTO.getProductPrice());

        if (productDetailDTO.getProductSizeId() != null) {
            ProductSize productSize = productSizeRepository.findById(productDetailDTO.getProductSizeId())
                    .orElseThrow(() -> new RuntimeException("ProductSize not found"));
            productDetail.setProductSize(productSize);
        }   // Check if productSizeId is not null before fetching the ProductSize

        productDetail = productDetailRepository.save(productDetail);    // Save the ProductDetail entity in the database
        productDetailDTO.setId(productDetail.getId());  // Set the id of the saved ProductDetail to the ProductDetailDTO

        List<ProductCondimentDetail> productCondimentDetails = productDetailDTO.getProductCondimentDetails().stream()
                .map(productCondimentDetailMapper::productCondimentDetailDTOToEntity)
                .map(productCondimentDetailRepository::save)
                .collect(Collectors.toList());  // ProductCondimentDetailDTO is converted to ProductCondimentDetail
        productDetail.setProductCondimentDetails(productCondimentDetails);

        return productDetail;
    }
}
