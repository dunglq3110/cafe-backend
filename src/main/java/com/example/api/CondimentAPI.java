package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.codiment.CondimentCreateRequest;

import com.example.dto.codiment.CondimentResponse;
import com.example.dto.codiment.CondimentUpdateRequest;
import com.example.service.ICondimentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condiment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CondimentAPI {

    ICondimentService condimentService;

    @PostMapping
    public ApiResponse<CondimentResponse> createCondiment(@RequestBody @Valid CondimentCreateRequest condimentCreateRequest) {
        return ApiResponse.<CondimentResponse>builder()
                .result(condimentService.createCondiment(condimentCreateRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CondimentResponse>> findAll() {
        return ApiResponse.<List<CondimentResponse>>builder()
                .result(condimentService.findAll())
                .build();
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<CondimentResponse> findById(@PathVariable("id") Long id ) {
        return ApiResponse.<CondimentResponse>builder()
                .result(condimentService.findById(id))
                .build();
    }

    @PutMapping(value = "/{id}")
    public ApiResponse<CondimentResponse> updateCondiment(@PathVariable("id") Long id, @RequestBody @Valid CondimentUpdateRequest condimentUpdateRequest) {
        return ApiResponse.<CondimentResponse>builder()
                .result(condimentService.updateCondiment(id, condimentUpdateRequest))
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse<Boolean> deleteCondiment(@PathVariable("id") Long id) {
        return ApiResponse.<Boolean>builder()
                .result(condimentService.deleteCondiment(id))
                .message("Condiment deleted successfully")
                .build();
    }
}
