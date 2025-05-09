package com.mycompany.property_management.repository;

import com.mycompany.property_management.dto.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
