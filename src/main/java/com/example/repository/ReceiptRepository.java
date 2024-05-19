package com.example.repository;

import com.example.entity.Receipt;
import com.example.entity.Staff;
import com.example.util.ReceiptStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Receipt findReceiptById(Long id);
    List<Receipt> findReceiptByStaffAndReceiptStatusOrderByDateDesc(Staff staff, ReceiptStatus receiptStatus);
    List<Receipt> findAllByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);

}
