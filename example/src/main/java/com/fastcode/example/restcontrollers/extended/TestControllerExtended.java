package com.fastcode.example.restcontrollers.extended;

import com.fastcode.example.application.extended.test.ITestAppServiceExtended;
import com.fastcode.example.commons.logging.LoggingHelper;
import com.fastcode.example.restcontrollers.core.TestController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/extended")
public class TestControllerExtended extends TestController {

    public TestControllerExtended(
        ITestAppServiceExtended testAppServiceExtended,
        LoggingHelper helper,
        Environment env
    ) {
        super(testAppServiceExtended, helper, env);
    }
    //Add your custom code here

}
