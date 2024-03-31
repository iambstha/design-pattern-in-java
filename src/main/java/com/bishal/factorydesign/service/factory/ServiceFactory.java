package com.bishal.factorydesign.service.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bishal.factorydesign.dto.StudentDTO;
import com.bishal.factorydesign.dto.TeacherDTO;
import com.bishal.factorydesign.service.template.ServiceTemplate;

@Component
public class ServiceFactory {

	private final Map<String, AbstractServiceFactory> serviceFactoryMap;

	public ServiceFactory(List<AbstractServiceFactory> serviceFactories) {
		this.serviceFactoryMap = new HashMap<>();
		for (AbstractServiceFactory factory : serviceFactories) {
			serviceFactoryMap.put(factory.getClass().getSimpleName(), factory);
		}
	}

	public ServiceTemplate<StudentDTO> createStudentService() {
		AbstractServiceFactory factory = serviceFactoryMap.get("studentServiceFactory");
		if (factory == null) {
			throw new IllegalArgumentException("Service type not supported");
		}
		return factory.createStudentService();
	}

	public ServiceTemplate<TeacherDTO> createTeacherService() {
		AbstractServiceFactory factory = serviceFactoryMap.get("teacherServiceFactory");
		if (factory == null) {
			throw new IllegalArgumentException("Service type not supported");
		}
		return factory.createTeacherService();
	}
}
