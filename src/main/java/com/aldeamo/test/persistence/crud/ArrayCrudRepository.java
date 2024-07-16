package com.aldeamo.test.persistence.crud;

import com.aldeamo.test.persistence.entity.Array;
import org.springframework.data.repository.CrudRepository;

public interface ArrayCrudRepository extends CrudRepository<Array, Integer> {
}
