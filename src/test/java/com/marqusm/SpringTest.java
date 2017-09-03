package com.marqusm;

import com.marqusm.configuration.TestSpringConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 31-Aug-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestSpringConfiguration.class)
@TestPropertySource(locations = "classpath:test.properties")
abstract public class SpringTest {

}
