package com.chu.user.vm;

import com.chu.user.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVM {
    private long id;
    private String username;
    private String displayname;
    private String image;

    public UserVM(UserModel user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setDisplayname(user.getDisplayname());
        this.setImage(user.getImage());
    }


}
