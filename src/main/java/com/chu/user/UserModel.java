package com.chu.user;



import com.chu.hoax.Hoax;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.beans.Transient;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class UserModel implements UserDetails {
        @Id
        @GeneratedValue
        private long id;

        @NotNull(message = "{chu.constraints.username.NotNull.message}")
        @Size(min = 4,max = 255)
        @UniqueUserName
        private String username;

        @NotNull(message = "{chu.constraints.displayname.NotNull.message}")
        @Size(min = 4,max = 255)
        private String displayname;

        @NotNull
        @Size(min = 8,max = 255)
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "{chu.constraints.password.Pattern.message}")
        private String password;


        private String image;


        @OneToMany(mappedBy = "userModel")
        private List<Hoax> hoaxes;

        @Override
        @Transient
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return AuthorityUtils.createAuthorityList("Role_USER");
        }

        @Override
        @Transient
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        @Transient
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        @Transient
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        @Transient
        public boolean isEnabled() {
                return true;
        }
}
