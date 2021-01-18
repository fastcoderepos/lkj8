package com.fastcode.example.domain.core.test;

import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository("testRepository")
public interface ITestRepository extends JpaRepository<TestEntity, Integer>, QuerydslPredicateExecutor<TestEntity> {}
