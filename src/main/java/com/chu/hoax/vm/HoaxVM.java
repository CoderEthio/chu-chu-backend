package com.chu.hoax.vm;

import com.chu.file.vm.FileAttachmentVM;
import com.chu.hoax.Hoax;
import com.chu.user.vm.UserVM;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class HoaxVM {

    private long id;
    private String content;
    private long date;
    private UserVM userVM;
    private FileAttachmentVM fileAttachmentVM;

    public HoaxVM(Hoax hoax) {
        this.setId(hoax.getId());
        this.setContent(hoax.getContent());
        this.setDate(hoax.getTimestamp().getTime());
        this.setUserVM(new UserVM(hoax.getUserModel()));
        if(hoax.getFileAttachemment()!=null){
            this.setFileAttachmentVM(new FileAttachmentVM(hoax.getFileAttachemment()));
        }

    }
}
