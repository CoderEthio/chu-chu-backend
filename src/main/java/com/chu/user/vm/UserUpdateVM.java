package com.chu.user.vm;

import com.chu.shared.ProfileImage;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateVM {

    @NotNull
    @Size(min = 4,max = 255)
    private String displayname;

    @ProfileImage
    private String image;
}
