package com.chu.hoax;

import com.chu.file.FileAttachemment;
import com.chu.file.FileAttachmentRepository;
import com.chu.file.FileService;
import com.chu.user.UserModel;
import com.chu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HoaxService {
    final
    HoaxRepository hoaxRepository;

    final
    UserService userService;

    final
    FileAttachmentRepository fileAttachmentRepository;
    final
    FileService fileService;

    public HoaxService(HoaxRepository hoaxRepository, UserService userService, FileAttachmentRepository fileAttachmentRepository, FileService fileService) {
        super();
        this.hoaxRepository = hoaxRepository;
        this.userService = userService;
        this.fileAttachmentRepository = fileAttachmentRepository;
        this.fileService = fileService;
    }

    public Hoax save(UserModel userModel, Hoax hoax){
        hoax.setTimestamp(new Date());
        hoax.setUserModel(userModel);
        if(hoax.getFileAttachemment()!=null){
            FileAttachemment inDb = fileAttachmentRepository.findById(hoax.getFileAttachemment().getId()).get();
            inDb.setHoax(hoax);
            hoax.setFileAttachemment(inDb);
        }
        return hoaxRepository.save(hoax);
    }

    public Page<Hoax> getAllHoaxes(Pageable pageable) {
        return hoaxRepository.findAll(pageable);
    }

    public Page<Hoax> getHoaxesOfUser(String username, Pageable pageable) {
        UserModel inDb = userService.getUserByUserName(username);
        return  hoaxRepository.findByUserModel(inDb,pageable);
    }



//
    public Page<Hoax> getOldHoaxes(long id, String username, Pageable pageable) {

        Specification<Hoax> specification = Specification.where(idLessThan(id));

        if(username!=null){
            UserModel inDb = userService.getUserByUserName(username);
            specification = specification.and(userIs(inDb));

        }
        //return hoaxRepository.findByIdLessThan(id,pageable);
        return hoaxRepository.findAll(specification,pageable);

    }


    //

    public List<Hoax> getNewHoaxes(long id, String username, Pageable pageable) {
        Specification<Hoax> specification = Specification.where(idGeaterThan(id));

        if(username!=null){
            UserModel inDb = userService.getUserByUserName(username);
            specification = specification.and(userIs(inDb));
        }


        return hoaxRepository.findAll(specification,pageable.getSort());

    }


//
    public long getNewHoaxesCount(long id, String username) {
        Specification<Hoax> specification = Specification.where(idGeaterThan(id));

        if(username!=null){
            UserModel inDb = userService.getUserByUserName(username);
            specification = specification.and(userIs(inDb));
        }
        return hoaxRepository.count(specification);
    }


    //
   private Specification<Hoax> userIs(UserModel userModel){
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("userModel"),userModel);
        };
   }
    private Specification<Hoax> idLessThan(long id){
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(root.get("id"),id);
        };
    }

    private Specification<Hoax> idGeaterThan(long id){
        return (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("id"),id);
        };
    }

    public void deletHoax(long id) {
        Hoax hoax = hoaxRepository.getOne(id);
        if(hoax.getFileAttachemment()!=null){
            fileService.deleteAttachmentImage(hoax.getFileAttachemment().getFileName());
        }
        hoaxRepository.deleteById(id);
    }
}
