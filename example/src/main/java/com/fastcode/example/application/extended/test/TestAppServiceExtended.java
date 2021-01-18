package com.fastcode.example.application.extended.test;

import com.fastcode.example.application.core.test.TestAppService;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.domain.extended.test.ITestRepositoryExtended;
import org.springframework.stereotype.Service;

@Service("testAppServiceExtended")
public class TestAppServiceExtended extends TestAppService implements ITestAppServiceExtended {

    public TestAppServiceExtended(
        ITestRepositoryExtended testRepositoryExtended,
        ITestMapperExtended mapper,
        LoggingHelper logHelper
    ) {
        super(testRepositoryExtended, mapper, logHelper);
    }
    //Add your custom code here

}
