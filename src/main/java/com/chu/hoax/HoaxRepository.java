package com.chu.hoax;

import com.chu.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaxRepository extends JpaRepository<Hoax , Long> , JpaSpecificationExecutor<Hoax> {
        Page<Hoax> findByUserModel(UserModel userModel, Pageable pageable);
/*
            //findByIdLessThan
       Page<Hoax> findByIdLessThan(long id,Pageable pageable);
       Page<Hoax> findByIdLessThanAndUserModel(long id,UserModel userModel,Pageable pageable);
            //findByIdGreaterThan
       List<Hoax> findByIdGreaterThan(long id, Sort sort);
       List<Hoax> findByIdGreaterThanAndUserModel(long id,UserModel userModel,Sort sort);
            //countByIdGreaterThan
       long countByIdGreaterThan(long id);
       long countByIdGreaterThanAndUserModel(long id,UserModel userModel);

 */

}
