package com.fastcode.example.application.core.test;

import com.fastcode.example.application.core.test.dto.*;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.commons.search.*;
import com.fastcode.example.domain.core.test.ITestRepository;
import com.fastcode.example.domain.core.test.QTestEntity;
import com.fastcode.example.domain.core.test.TestEntity;
import com.querydsl.core.BooleanBuilder;
import java.time.*;
import java.util.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("testAppService")
@RequiredArgsConstructor
public class TestAppService implements ITestAppService {

    @Qualifier("testRepository")
    @NonNull
    protected final ITestRepository _testRepository;

    @Qualifier("ITestMapperImpl")
    @NonNull
    protected final ITestMapper mapper;

    @NonNull
    protected final LoggingHelper logHelper;

    @Transactional(propagation = Propagation.REQUIRED)
    public CreateTestOutput create(CreateTestInput input) {
        TestEntity test = mapper.createTestInputToTestEntity(input);

        TestEntity createdTest = _testRepository.save(test);
        return mapper.testEntityToCreateTestOutput(createdTest);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UpdateTestOutput update(Integer testId, UpdateTestInput input) {
        TestEntity test = mapper.updateTestInputToTestEntity(input);

        TestEntity updatedTest = _testRepository.save(test);
        return mapper.testEntityToUpdateTestOutput(updatedTest);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer testId) {
        TestEntity existing = _testRepository.findById(testId).orElse(null);
        _testRepository.delete(existing);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public FindTestByIdOutput findById(Integer testId) {
        TestEntity foundTest = _testRepository.findById(testId).orElse(null);
        if (foundTest == null) return null;

        return mapper.testEntityToFindTestByIdOutput(foundTest);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<FindTestByIdOutput> find(SearchCriteria search, Pageable pageable) throws Exception {
        Page<TestEntity> foundTest = _testRepository.findAll(search(search), pageable);
        List<TestEntity> testList = foundTest.getContent();
        Iterator<TestEntity> testIterator = testList.iterator();
        List<FindTestByIdOutput> output = new ArrayList<>();

        while (testIterator.hasNext()) {
            TestEntity test = testIterator.next();
            output.add(mapper.testEntityToFindTestByIdOutput(test));
        }
        return output;
    }

    protected BooleanBuilder search(SearchCriteria search) throws Exception {
        QTestEntity test = QTestEntity.testEntity;
        if (search != null) {
            Map<String, SearchFields> map = new HashMap<>();
            for (SearchFields fieldDetails : search.getFields()) {
                map.put(fieldDetails.getFieldName(), fieldDetails);
            }
            List<String> keysList = new ArrayList<String>(map.keySet());
            checkProperties(keysList);
            return searchKeyValuePair(test, map, search.getJoinColumns());
        }
        return null;
    }

    protected void checkProperties(List<String> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (
                !(
                    list.get(i).replace("%20", "").trim().equals("id") ||
                    list.get(i).replace("%20", "").trim().equals("version")
                )
            ) {
                throw new Exception("Wrong URL Format: Property " + list.get(i) + " not found!");
            }
        }
    }

    protected BooleanBuilder searchKeyValuePair(
        QTestEntity test,
        Map<String, SearchFields> map,
        Map<String, String> joinColumns
    ) {
        BooleanBuilder builder = new BooleanBuilder();

        for (Map.Entry<String, SearchFields> details : map.entrySet()) {
            if (details.getKey().replace("%20", "").trim().equals("id")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test.id.eq(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test.id.ne(Integer.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        test.id.between(
                            Integer.valueOf(details.getValue().getStartingValue()),
                            Integer.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        test.id.goe(Integer.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        test.id.loe(Integer.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
            if (details.getKey().replace("%20", "").trim().equals("version")) {
                if (
                    details.getValue().getOperator().equals("equals") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test.version.eq(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("notEqual") &&
                    StringUtils.isNumeric(details.getValue().getSearchValue())
                ) builder.and(test.version.ne(Long.valueOf(details.getValue().getSearchValue()))); else if (
                    details.getValue().getOperator().equals("range")
                ) {
                    if (
                        StringUtils.isNumeric(details.getValue().getStartingValue()) &&
                        StringUtils.isNumeric(details.getValue().getEndingValue())
                    ) builder.and(
                        test.version.between(
                            Long.valueOf(details.getValue().getStartingValue()),
                            Long.valueOf(details.getValue().getEndingValue())
                        )
                    ); else if (StringUtils.isNumeric(details.getValue().getStartingValue())) builder.and(
                        test.version.goe(Long.valueOf(details.getValue().getStartingValue()))
                    ); else if (StringUtils.isNumeric(details.getValue().getEndingValue())) builder.and(
                        test.version.loe(Long.valueOf(details.getValue().getEndingValue()))
                    );
                }
            }
        }

        return builder;
    }
}
