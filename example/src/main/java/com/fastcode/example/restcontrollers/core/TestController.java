package com.fastcode.example.restcontrollers.core;

import com.fastcode.example.application.core.test.ITestAppService;
import com.fastcode.example.application.core.test.dto.*;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.commons.search.OffsetBasedPageRequest;
import com.fastcode.example.commons.search.SearchCriteria;
import com.fastcode.example.commons.search.SearchUtils;
import java.time.*;
import java.util.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @Qualifier("testAppService")
    @NonNull
    protected final ITestAppService _testAppService;

    @NonNull
    protected final LoggingHelper logHelper;

    @NonNull
    protected final Environment env;

    @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity<CreateTestOutput> create(@RequestBody @Valid CreateTestInput test) {
        CreateTestOutput output = _testAppService.create(test);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    // ------------ Delete test ------------
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/json" })
    public void delete(@PathVariable String id) {
        FindTestByIdOutput output = _testAppService.findById(Integer.valueOf(id));
        Optional
            .ofNullable(output)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("There does not exist a test with a id=%s", id))
            );

        _testAppService.delete(Integer.valueOf(id));
    }

    // ------------ Update test ------------
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<UpdateTestOutput> update(@PathVariable String id, @RequestBody @Valid UpdateTestInput test) {
        FindTestByIdOutput currentTest = _testAppService.findById(Integer.valueOf(id));
        Optional
            .ofNullable(currentTest)
            .orElseThrow(
                () -> new EntityNotFoundException(String.format("Unable to update. Test with id=%s not found.", id))
            );

        test.setVersiono(currentTest.getVersiono());
        UpdateTestOutput output = _testAppService.update(Integer.valueOf(id), test);
        return new ResponseEntity(output, HttpStatus.OK);
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        consumes = { "application/json" },
        produces = { "application/json" }
    )
    public ResponseEntity<FindTestByIdOutput> findById(@PathVariable String id) {
        FindTestByIdOutput output = _testAppService.findById(Integer.valueOf(id));
        Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

        return new ResponseEntity(output, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity find(
        @RequestParam(value = "search", required = false) String search,
        @RequestParam(value = "offset", required = false) String offset,
        @RequestParam(value = "limit", required = false) String limit,
        Sort sort
    )
        throws Exception {
        if (offset == null) {
            offset = env.getProperty("fastCode.offset.default");
        }
        if (limit == null) {
            limit = env.getProperty("fastCode.limit.default");
        }

        if (sort == null || sort.isEmpty()) {
            sort = Sort.by(Sort.Direction.ASC, "id");
        }

        Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
        SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);

        return ResponseEntity.ok(_testAppService.find(searchCriteria, Pageable));
    }
}
