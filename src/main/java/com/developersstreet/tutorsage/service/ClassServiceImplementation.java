package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClassServiceImplementation implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public Class getClassById(Long id) {
        return classRepository.findClassById(id);
    }

    @Override
    public List<Class> getClassesByQueryAndOffsetAndLimit(String query, Long offset, Long limit) throws Exception {
        List<Class> classes = classRepository.findClassesByNameContains(query);
        if(classes.size() == 0) throw new Exception("No Classes found");
        Long fromIndex = limit * (offset - 1);
        Long toIndex = limit * (offset - 1) + limit;
        if(classes.size() < toIndex) classes = classes.subList(Integer.parseInt(fromIndex.toString()), classes.size());
        else classes = classes.subList(Integer.parseInt(fromIndex.toString()), Integer.parseInt(toIndex.toString()));
        return classes;
    }

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
