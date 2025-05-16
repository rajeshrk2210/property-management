package com.mycompany.property_management.repository;

import com.mycompany.property_management.entity.PropertyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
//    @Query("SELECT p FROM PropertyEntity p WHERE p.userEntity.id = :userId AND p.title = :title")
//    List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId, @Param("title") Long title);
    List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId);
}
