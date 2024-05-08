package com.example.api;

import com.example.dto.CondimentDTO;
import com.example.service.ICondimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/condiment")
public class CondimentAPI {

    @Autowired
    ICondimentService condimentService;

    @PostMapping
    public CondimentDTO save(@RequestBody CondimentDTO condimentDTO) {
        return condimentService.save(condimentDTO);
    }

    @GetMapping
    public List<CondimentDTO> getAllCondiments() {
        return condimentService.getAllCondiments();
    }
}
