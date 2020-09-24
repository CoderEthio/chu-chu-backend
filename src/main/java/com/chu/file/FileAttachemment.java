package com.chu.file;

import com.chu.hoax.Hoax;
import com.chu.shared.ProfileImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class FileAttachemment {

    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private  String fileName;

    private String fileType;

    @OneToOne
    private Hoax hoax;

}
