package com.fastcode.example.domain.extended.test;

import com.fastcode.example.domain.core.test.ITestRepository;
import org.springframework.stereotype.Repository;

@Repository("testRepositoryExtended")
public interface ITestRepositoryExtended extends ITestRepository {
    //Add your custom code here
}
