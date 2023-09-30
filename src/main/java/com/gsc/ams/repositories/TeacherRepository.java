package com.gsc.ams.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gsc.ams.entities.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
	
}

