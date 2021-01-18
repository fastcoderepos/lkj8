package com.fastcode.example.application.core.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fastcode.example.application.core.test.dto.*;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.commons.search.*;
import com.fastcode.example.domain.core.test.*;
import com.fastcode.example.domain.core.test.QTestEntity;
import com.fastcode.example.domain.core.test.TestEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestAppServiceTest {

    @InjectMocks
    @Spy
    protected TestAppService _appService;

    @Mock
    protected ITestRepository _testRepository;

    @Mock
    protected ITestMapper _mapper;

    @Mock
    protected Logger loggerMock;

    @Mock
    protected LoggingHelper logHelper;

    protected static Integer ID = 15;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(_appService);
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());
    }

    @Test
    public void findTestById_IdIsNotNullAndIdDoesNotExist_ReturnNull() {
        Optional<TestEntity> nullOptional = Optional.ofNullable(null);
        Mockito.when(_testRepository.findById(any(Integer.class))).thenReturn(nullOptional);
        Assertions.assertThat(_appService.findById(ID)).isEqualTo(null);
    }

    @Test
    public void findTestById_IdIsNotNullAndIdExists_ReturnTest() {
        TestEntity test = mock(TestEntity.class);
        Optional<TestEntity> testOptional = Optional.of((TestEntity) test);
        Mockito.when(_testRepository.findById(any(Integer.class))).thenReturn(testOptional);

        Assertions.assertThat(_appService.findById(ID)).isEqualTo(_mapper.testEntityToFindTestByIdOutput(test));
    }

    @Test
    public void createTest_TestIsNotNullAndTestDoesNotExist_StoreTest() {
        TestEntity testEntity = mock(TestEntity.class);
        CreateTestInput testInput = new CreateTestInput();

        Mockito.when(_mapper.createTestInputToTestEntity(any(CreateTestInput.class))).thenReturn(testEntity);
        Mockito.when(_testRepository.save(any(TestEntity.class))).thenReturn(testEntity);

        Assertions
            .assertThat(_appService.create(testInput))
            .isEqualTo(_mapper.testEntityToCreateTestOutput(testEntity));
    }

    @Test
    public void updateTest_TestIdIsNotNullAndIdExists_ReturnUpdatedTest() {
        TestEntity testEntity = mock(TestEntity.class);
        UpdateTestInput test = mock(UpdateTestInput.class);

        Mockito.when(_mapper.updateTestInputToTestEntity(any(UpdateTestInput.class))).thenReturn(testEntity);
        Mockito.when(_testRepository.save(any(TestEntity.class))).thenReturn(testEntity);
        Assertions.assertThat(_appService.update(ID, test)).isEqualTo(_mapper.testEntityToUpdateTestOutput(testEntity));
    }

    @Test
    public void deleteTest_TestIsNotNullAndTestExists_TestRemoved() {
        TestEntity test = mock(TestEntity.class);
        Optional<TestEntity> testOptional = Optional.of((TestEntity) test);
        Mockito.when(_testRepository.findById(any(Integer.class))).thenReturn(testOptional);

        _appService.delete(ID);
        verify(_testRepository).delete(test);
    }

    @Test
    public void find_ListIsEmpty_ReturnList() throws Exception {
        List<TestEntity> list = new ArrayList<>();
        Page<TestEntity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTestByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_testRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void find_ListIsNotEmpty_ReturnList() throws Exception {
        List<TestEntity> list = new ArrayList<>();
        TestEntity test = mock(TestEntity.class);
        list.add(test);
        Page<TestEntity> foundPage = new PageImpl(list);
        Pageable pageable = mock(Pageable.class);
        List<FindTestByIdOutput> output = new ArrayList<>();
        SearchCriteria search = new SearchCriteria();
        //		search.setType(1);
        //		search.setValue("xyz");
        //		search.setOperator("equals");
        output.add(_mapper.testEntityToFindTestByIdOutput(test));

        Mockito.when(_appService.search(any(SearchCriteria.class))).thenReturn(new BooleanBuilder());
        Mockito.when(_testRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);
        Assertions.assertThat(_appService.find(search, pageable)).isEqualTo(output);
    }

    @Test
    public void searchKeyValuePair_PropertyExists_ReturnBooleanBuilder() {
        QTestEntity test = QTestEntity.testEntity;
        SearchFields searchFields = new SearchFields();
        searchFields.setOperator("equals");
        searchFields.setSearchValue("xyz");
        Map<String, SearchFields> map = new HashMap<>();
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("xyz", String.valueOf(ID));
        BooleanBuilder builder = new BooleanBuilder();
        Assertions.assertThat(_appService.searchKeyValuePair(test, map, searchMap)).isEqualTo(builder);
    }

    @Test(expected = Exception.class)
    public void checkProperties_PropertyDoesNotExist_ThrowException() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("xyz");
        _appService.checkProperties(list);
    }

    @Test
    public void checkProperties_PropertyExists_ReturnNothing() throws Exception {
        List<String> list = new ArrayList<>();
        _appService.checkProperties(list);
    }

    @Test
    public void search_SearchIsNotNullAndSearchContainsCaseThree_ReturnBooleanBuilder() throws Exception {
        Map<String, SearchFields> map = new HashMap<>();
        QTestEntity test = QTestEntity.testEntity;
        List<SearchFields> fieldsList = new ArrayList<>();
        SearchFields fields = new SearchFields();
        SearchCriteria search = new SearchCriteria();
        search.setType(3);
        search.setValue("xyz");
        search.setOperator("equals");
        fields.setOperator("equals");
        fields.setSearchValue("xyz");
        fieldsList.add(fields);
        search.setFields(fieldsList);
        BooleanBuilder builder = new BooleanBuilder();
        Mockito.doNothing().when(_appService).checkProperties(any(List.class));
        Mockito
            .doReturn(builder)
            .when(_appService)
            .searchKeyValuePair(any(QTestEntity.class), any(HashMap.class), any(HashMap.class));

        Assertions.assertThat(_appService.search(search)).isEqualTo(builder);
    }

    @Test
    public void search_StringIsNull_ReturnNull() throws Exception {
        Assertions.assertThat(_appService.search(null)).isEqualTo(null);
    }
}
