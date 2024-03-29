package com.bishal.factorydesign.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bishal.factorydesign.mapper.StudentMapper;
import com.bishal.factorydesign.mapper.TeacherMapper;

@Configuration
public class AppConfig {

	@Bean
    public StudentMapper studentMapper() {
        return Mappers.getMapper(StudentMapper.class);
    }
	
    @Bean
    public TeacherMapper teacherMapper() {
        return Mappers.getMapper(TeacherMapper.class);
    }
}
