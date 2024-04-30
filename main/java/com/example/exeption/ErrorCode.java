package com.example.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
public enum ErrorCode {

    UNCATEGORIZED(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID(9998, "Key is invalid", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001, "User is existed",HttpStatus.BAD_REQUEST),
    USER_NOTFOUND(1002, "User not found",HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1003, "Username must be at least 8 characters and maximum 20 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters and maximum 20 characters",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You are not allowed!", HttpStatus.FORBIDDEN),
    WRONG_PASSWORD(1007,"The password is wrong!!", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1008, "Product not found", HttpStatus.NOT_FOUND),
    CONDIMENTS_NOT_FOUND(1009, "Condiments not found", HttpStatus.NOT_FOUND),
    SIZE_NOT_FOUND(1010, "Size not found", HttpStatus.NOT_FOUND),
    PRODUCT_CREATE_SUCCESSFULLY(1011, "Product create successfully", HttpStatus.OK),
    PRODUCT_DELETE_SUCCESSFULLY(1012, "Product delete successfully", HttpStatus.OK),
    PRODUCT_UPDATE_SUCCESSFULLY(1013, "Product update successfully", HttpStatus.OK),
    ORDER_PLACED_SUCCESSFULLY(200, "Order placed successfully", HttpStatus.OK),
    ORDER_DELETED_SUCCESSFULLY(201, "Order deleted successfully", HttpStatus.OK),
    CART_IS_EMPTY(202, "Cart is empty", HttpStatus.BAD_REQUEST),
    INVALID_INDEX(203, "Order not found", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(204, "Invalid token", HttpStatus.BAD_REQUEST),
    ;
    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}