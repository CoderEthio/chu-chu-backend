package com.chu.hoax;

import com.chu.file.FileAttachemment;
import com.chu.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Hoax {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 10,max = 5000)
    @Column(length = 5000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private UserModel userModel;

    @OneToOne(mappedBy = "hoax",orphanRemoval = true)
    private FileAttachemment fileAttachemment;

}
