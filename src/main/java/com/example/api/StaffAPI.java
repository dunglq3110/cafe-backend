package com.example.api;

import com.example.dto.StaffDTO;
import com.example.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/staff")
public class StaffAPI {
    @Autowired
    private IStaffService staffService;

    @PostMapping
    private StaffDTO createStaff(@RequestBody StaffDTO staffDTO) {
        return staffService.save(staffDTO);
    }

    @PutMapping (value = "/{id}")
    private StaffDTO updateStaff (@RequestBody StaffDTO staffDTO, @PathVariable ("id") Long id) {
        staffDTO.setId(id);
        return staffService.save(staffDTO);

    }

    @GetMapping
    private List<StaffDTO> findAll() {
        return staffService.findAll();
    }
}
