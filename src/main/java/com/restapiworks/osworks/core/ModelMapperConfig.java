package com.restapiworks.osworks.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig extends ModelMapper {
	
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
