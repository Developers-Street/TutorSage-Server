package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClassServiceImplementation implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public Class createClass(Class c) {
        return classRepository.save(c);
    }

    @Override
    public void addMemberToClass(Long classId, User user) {
         Class c = classRepository.findClassById(classId);
         c.getMembers().add(user);
    }
}
