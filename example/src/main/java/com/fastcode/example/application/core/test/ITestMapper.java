package com.fastcode.example.application.core.test;

import com.fastcode.example.application.core.test.dto.*;
import com.fastcode.example.domain.core.test.TestEntity;
import java.time.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITestMapper {
    TestEntity createTestInputToTestEntity(CreateTestInput testDto);

    CreateTestOutput testEntityToCreateTestOutput(TestEntity entity);

    TestEntity updateTestInputToTestEntity(UpdateTestInput testDto);

    UpdateTestOutput testEntityToUpdateTestOutput(TestEntity entity);

    FindTestByIdOutput testEntityToFindTestByIdOutput(TestEntity entity);
}
