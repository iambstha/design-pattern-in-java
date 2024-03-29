package com.bishal.factorydesign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bishal.factorydesign.entity.Person;

@Repository
public interface PersonRepository<T extends Person> extends JpaRepository<T, Integer> {

}
