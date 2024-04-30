package com.example.service;

import com.example.dto.staff.StaffCreationRequest;
import com.example.dto.staff.StaffResponse;
import com.example.dto.staff.StaffUpdateRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IStaffService {
    StaffResponse createStaff(StaffCreationRequest request);
    StaffResponse createManager(StaffCreationRequest request);
    List<StaffResponse> findAll();
    StaffResponse findStaffById(Long id);
    StaffResponse updateStaff(Long id, StaffUpdateRequest request);
    StaffResponse updateStaffInfo(StaffUpdateRequest request);
    boolean deleteStaff(Long id);
    StaffResponse getMyInfo();
}
