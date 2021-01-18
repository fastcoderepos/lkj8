package com.fastcode.example.application.extended.test;

import com.fastcode.example.application.core.test.ITestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestMapperExtended extends ITestMapper {}
