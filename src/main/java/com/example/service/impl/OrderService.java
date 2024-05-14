package com.example.service.impl;

import com.example.dto.order.*;
import com.example.dto.receipt.ReceiptResponse;
import com.example.entity.*;
import com.example.exeption.AppException;
import com.example.exeption.ErrorCode;
import com.example.mapper.ReceiptMapper;
import com.example.repository.*;
import com.example.service.IOrderService;
import com.example.util.ReceiptStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {

    ReceiptRepository receiptRepository;
    StaffRepository staffRepository;
    CustomerRepository customerRepository;
    ProductRepository productRepository;
    ReceiptMapper receiptMapper;
    SizeRepository sizeRepository;
    ProductSizeRepository productSizeRepository;
    ProductDetailRepository productDetailRepository;
    CondimentRepository condimentRepository;
    ProductCondimentDetailRepository productCondimentDetailRepository;

    @Override
    public ReceiptResponse createNewOrder() {
        //get current staff credential through token
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Staff staff = staffRepository.findStaffByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        LocalDateTime localDateTime = LocalDateTime.now();

        //mapper
        Receipt receipt = new Receipt();
        receipt.setDiscount(0);
        receipt.setTotalPrice(0);
        receipt.setStaff(staff);
        receipt.setReceiptStatus(ReceiptStatus.PROGRESS);
        receipt.setDate(localDateTime);

        //save and return
        receipt = receiptRepository.save(receipt);
        return receiptMapper.toResponse(receipt);
    }

    @Override
    public ReceiptResponse updateCustomerReceipt(UpdateCustomerReceiptRequest updateCustomerReceiptRequest) {
        Receipt receipt = receiptRepository.findReceiptById(updateCustomerReceiptRequest.getReceiptId());
        Customer customer = customerRepository.findCustomersById(updateCustomerReceiptRequest.getCustomerId());
        receipt.setCustomer(customer);
        receipt = receiptRepository.save(receipt);
        return receiptMapper.toResponse(receipt);
    }

    @Override
    public ReceiptResponse addProductReceipt(AddProductReceiptRequest addProductReceiptRequest) {
        //find objects in database
        Product product = productRepository.findById(addProductReceiptRequest.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        Size size = sizeRepository.findById(addProductReceiptRequest.getSizeId()).orElseThrow(() -> new AppException((ErrorCode.SIZE_NOT_FOUND)));
        Receipt receipt = receiptRepository.findReceiptById(addProductReceiptRequest.getReceiptId());
        ProductSize productSize = productSizeRepository.findProductSizeByProductAndSize(product, size);

        //mapper
        ProductDetail productDetail = new ProductDetail();
        productDetail.setReceipt(receipt);
        productDetail.setProductSize(productSize);
        productDetail.setProductQuantity(addProductReceiptRequest.getQuantity());
        productDetail.setProductPrice(productSize.getPrice());
        productDetail.setProductDiscount(product.getDiscount());

        productDetail = productDetailRepository.save(productDetail);
        return receiptMapper.toResponse(receiptRepository.findReceiptById(receipt.getId()));
    }

    @Override
    public ReceiptResponse addCondimentReceipt(AddCondimentReceiptRequest addCondimentReceiptRequest) {

        ProductDetail productDetail = productDetailRepository.findById(addCondimentReceiptRequest.getProductDetailId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED));
        Condiment condiment = condimentRepository.findById(addCondimentReceiptRequest.getCondimentId())
                .orElseThrow(() -> new AppException(ErrorCode.CONDIMENTS_NOT_FOUND));
        ProductCondimentDetail productCondimentDetail = new ProductCondimentDetail();

        //mapper
        productCondimentDetail.setCondiment(condiment);
        productCondimentDetail.setProductDetail(productDetail);
        productCondimentDetail.setQuantity(addCondimentReceiptRequest.getQuantity());
        productCondimentDetail.setCondimentPrice(condiment.getUnitPrice());


        //save
        productCondimentDetail = productCondimentDetailRepository.save(productCondimentDetail);
        Receipt receipt = productCondimentDetail.getProductDetail().getReceipt();
        return receiptMapper.toResponse(receipt);
    }

    @Override
    public ReceiptResponse updateProductReceipt(UpdateProductReceiptRequest updateProductReceiptRequest) {
        ProductDetail productDetail = productDetailRepository.findById(updateProductReceiptRequest.getProductDetailId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED));

        productDetail.setProductQuantity(updateProductReceiptRequest.getQuantity());
        productDetail = productDetailRepository.save(productDetail);

        return receiptMapper.toResponse(productDetail.getReceipt());
    }

    @Override
    public ReceiptResponse updateCondimentReceipt(UpdateCondimentReceiptRequest updateCondimentReceiptRequest) {
        ProductCondimentDetail productCondimentDetail = productCondimentDetailRepository.findById(updateCondimentReceiptRequest.getProductCondimentDetailId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED));

        productCondimentDetail.setQuantity(updateCondimentReceiptRequest.getQuantity());
        productCondimentDetail = productCondimentDetailRepository.save(productCondimentDetail);
        return receiptMapper.toResponse(productCondimentDetail.getProductDetail().getReceipt());
    }

    @Override
    public ReceiptResponse deleteProductReceipt(Long id) {
        Long receiptId = productDetailRepository.findById(id).get().getReceipt().getId();
        productDetailRepository.deleteById(id);
        return receiptMapper.toResponse(receiptRepository.findReceiptById(receiptId));
    }

    @Override
    public ReceiptResponse deleteCondimentReceipt(Long id) {
        Long receiptId = productCondimentDetailRepository.findById(id).get()
                .getProductDetail()
                .getReceipt()
                .getId();

        productCondimentDetailRepository.deleteById(id);
        return receiptMapper.toResponse(receiptRepository.findReceiptById(receiptId));
    }


}