package com.chu.hoax;

import com.chu.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HoaxSecurityService {
    final
    HoaxRepository hoaxRepository;

    public HoaxSecurityService(HoaxRepository hoaxRepository) {
        super();
        this.hoaxRepository = hoaxRepository;
    }

    public boolean isAllowedToDelete(long hoaxId, UserModel loggedInUserModel){
        Optional<Hoax> optionalHoax = hoaxRepository.findById(hoaxId);
        if(optionalHoax.isPresent()){
            Hoax inDb = optionalHoax.get();
            if(inDb.getUserModel().getId() == loggedInUserModel.getId()){
                return true;
            }
        }
        return false;
    }

}
