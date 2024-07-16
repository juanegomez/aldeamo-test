package com.aldeamo.test.persistence;

import com.aldeamo.test.persistence.crud.ArrayCrudRepository;
import com.aldeamo.test.persistence.entity.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ArrayRepository {
    @Autowired
    private ArrayCrudRepository arrayCrudRepository;

    public Optional<Array> getArray(int id){
        return arrayCrudRepository.findById(id);
    }
}