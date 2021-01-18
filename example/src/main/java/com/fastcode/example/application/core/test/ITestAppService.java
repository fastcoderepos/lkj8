package com.fastcode.example.application.core.test;

import com.fastcode.example.application.core.test.dto.*;
import com.fastcode.example.commons.search.SearchCriteria;
import java.util.*;
import org.springframework.data.domain.Pageable;

public interface ITestAppService {
    //CRUD Operations

    CreateTestOutput create(CreateTestInput test);

    void delete(Integer id);

    UpdateTestOutput update(Integer id, UpdateTestInput input);

    FindTestByIdOutput findById(Integer id);

    List<FindTestByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception;
    //Join Column Parsers
}
