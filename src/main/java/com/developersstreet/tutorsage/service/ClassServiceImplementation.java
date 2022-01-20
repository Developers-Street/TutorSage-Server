package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassServiceImplementation implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public Class createClass(Class c) {
        return classRepository.save(c);
    }
}
