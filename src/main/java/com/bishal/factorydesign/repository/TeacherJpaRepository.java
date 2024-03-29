package com.bishal.factorydesign.repository;

import org.springframework.stereotype.Repository;

import com.bishal.factorydesign.entity.Teacher;

@Repository
public interface TeacherJpaRepository extends PersonRepository<Teacher> {

}
