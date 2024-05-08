package com.example.mapper;

import com.example.dto.ProductCondimentDetailDTO;
import com.example.dto.cart.CondimentDetailsResponse;
import com.example.dto.cart.ProductDetailResponse;
import com.example.entity.Condiment;
import com.example.entity.ProductCondimentDetail;
import com.example.entity.ProductDetail;
import com.example.repository.CondimentRepository;
import com.example.repository.ProductCondimentDetailRepository;
import com.example.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductCondimentDetailMapper {

    @Autowired
    CondimentRepository condimentRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductCondimentDetailRepository productCondimentDetailRepository;

    public List<ProductCondimentDetailDTO> mapCondimentDetailsResponseToProductCondimentDetailDTO(CondimentDetailsResponse condimentDetailsResponse, Long productDetailId) {
        List<ProductCondimentDetailDTO> productCondimentDetailDTOs = new ArrayList<>();
        for (ProductDetailResponse productDetailResponse : condimentDetailsResponse.getCondiments()) {
            ProductCondimentDetailDTO productCondimentDetailDTO = new ProductCondimentDetailDTO();
            productCondimentDetailDTO.setProductDetailId(productDetailId);
            productCondimentDetailDTO.setQuantity(productDetailResponse.getQuantity());
            productCondimentDetailDTO.setCondimentPrice(productDetailResponse.getCondiment().getUnitPrice());
            productCondimentDetailDTO.setCondimentId(productDetailResponse.getCondiment().getId());
            productCondimentDetailDTOs.add(productCondimentDetailDTO);
        }
        return productCondimentDetailDTOs;
    }
    public ProductCondimentDetail productCondimentDetailDTOToEntity(ProductCondimentDetailDTO productCondimentDetailDTO) {
        ProductCondimentDetail productCondimentDetail = new ProductCondimentDetail();

        productCondimentDetail.setQuantity(productCondimentDetailDTO.getQuantity());
        productCondimentDetail.setCondimentPrice(productCondimentDetailDTO.getCondimentPrice());
        Condiment condiment = condimentRepository.findById(productCondimentDetailDTO.getCondimentId())
                .orElseThrow(() -> new RuntimeException("Condiment not found"));
        productCondimentDetail.setCondiment(condiment);

        // Check if productDetailId is not null before fetching the ProductDetail
        if (productCondimentDetailDTO.getProductDetailId() != null) {
            ProductDetail productDetail = productDetailRepository.findById(productCondimentDetailDTO.getProductDetailId())
                    .orElseThrow(() -> new RuntimeException("ProductDetail not found"));
            productCondimentDetail.setProductDetail(productDetail);
        }

        return productCondimentDetail;
    }
}
