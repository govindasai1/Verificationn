package com.example

import com.example.reposotories.UserServiceTest
import com.example.routingTest.UserRoutingTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    UserServiceTest::class,
    UserRoutingTest::class
)
class TestSuites