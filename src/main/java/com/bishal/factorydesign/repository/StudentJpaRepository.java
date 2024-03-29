package com.bishal.factorydesign.repository;

import org.springframework.stereotype.Repository;

import com.bishal.factorydesign.entity.Student;

@Repository
public interface StudentJpaRepository extends PersonRepository<Student> {

}
