package com.apiteste.apiteste.mapper;

import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}