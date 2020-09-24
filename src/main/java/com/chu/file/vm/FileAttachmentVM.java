package com.chu.file.vm;

import com.chu.file.FileAttachemment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileAttachmentVM {
    private String filename;
    private String fileType;

    public FileAttachmentVM(FileAttachemment fileAttachemment) {
        this.setFilename(fileAttachemment.getFileName());
        this.setFileType(fileAttachemment.getFileType());
    }
}
