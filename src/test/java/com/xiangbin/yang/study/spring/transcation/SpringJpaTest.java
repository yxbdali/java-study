package com.xiangbin.yang.study.spring.transcation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.xiangbin.yang.study.spring.SpringBootApp;
import com.xiangbin.yang.study.spring.transcation.model.Info;
import com.xiangbin.yang.study.spring.transcation.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static java.lang.System.out;

/**
 * @author xiangbin.yang
 * @since 2017/11/1
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class SpringJpaTest {
    @Autowired
    private ApplicationContext applicationContext;

    private static ExecutorService pool = Executors.newFixedThreadPool(2);


    @Test
    public void testIsolation() throws InterruptedException {
        //dbService.insert(new Info("1"));
        DbService dbService = applicationContext.getBean(DbService.class);
        pool.execute(() -> {
            try {
                dbService.update(1, "aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.SECONDS.sleep(2);
        //dbService.listAll().forEach(i -> log.info("{}", i));
        log.info("{}", dbService.find(1));

        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {

        }
    }

    @Test
    public void testDbDeadlock() throws InterruptedException {
        DbService dbService = applicationContext.getBean(DbService.class);
        /*
        Thread t1 = new Thread(() -> {
            try {
                dbService.update(1, "xiangbin.yang");
                //dbService.deadLock(1, "xiangbin.yang", 4, "yanping.lin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

        pool.execute(() -> {
            try {
                dbService.deadLock(1, "xiangbin.yang", 4, "yanping.lin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }});

        //Thread.sleep(1000);
        pool.execute(() -> {
            try {
                dbService.deadLock(4, "yanping.lin", 1, "yanping.lin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }});

            pool.shutdown();
            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {

            }
        /*
        Thread t2 = new Thread(() -> {
            try {
                dbService.deadLock(4, "xiangbin.yang", 1, "yanping.lin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        */

        //t1.start();
        //t2.start();
    }
}
