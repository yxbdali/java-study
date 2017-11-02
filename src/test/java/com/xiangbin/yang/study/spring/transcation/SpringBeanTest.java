package com.xiangbin.yang.study.spring.transcation;

import com.xiangbin.yang.study.spring.beans.Person;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiangbin.yang
 * @since 2017/11/2
 */
public class SpringBeanTest {
    @Test
    public void beanTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:TestBean.xml");
        Person person = applicationContext.getBean(Person.class);
        Assert.assertNotNull(person);
    }
}
