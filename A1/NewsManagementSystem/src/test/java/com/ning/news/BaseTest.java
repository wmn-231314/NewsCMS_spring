package com.ning.news;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@SpringBootTest
@RunWith(SpringRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring_config/spring-mvc.xml","classpath:spring_config/spring-dao.xml", "classpath:spring_config/spring-service.xml","classpath:spring_config/spring-trans.xml" })
public abstract class BaseTest {

}
