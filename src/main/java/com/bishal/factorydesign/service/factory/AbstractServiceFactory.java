package com.bishal.factorydesign.service.factory;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.service.template.ServiceTemplate;

public interface AbstractServiceFactory {

	ServiceTemplate<StudentDTO> createStudentService();

	ServiceTemplate<TeacherDTO> createTeacherService();
}
