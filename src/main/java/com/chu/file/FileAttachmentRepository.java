package com.chu.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface FileAttachmentRepository  extends JpaRepository<FileAttachemment,Long> {
        List<FileAttachemment> findByDateBeforeAndHoaxIsNotNull(Date date);
}
