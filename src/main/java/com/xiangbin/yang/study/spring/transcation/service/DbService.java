package com.xiangbin.yang.study.spring.transcation.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.xiangbin.yang.study.spring.transcation.dao.InfoRepo;
import com.xiangbin.yang.study.spring.transcation.model.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiangbin.yang
 * @since 2017/11/1
 */
@Slf4j
@Service
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public class DbService {
    @Autowired
    private InfoRepo infoRepo;

    public void insert(Info info) throws InterruptedException {
        infoRepo.save(info);
        infoRepo.flush();
        log.info("Wait for 10s...");
        TimeUnit.SECONDS.sleep(10);
        log.info("Wait finished!");
    }

    public Info find(long id) {
        return infoRepo.findOne(id);
    }

    public void update(long id, String s) throws InterruptedException {
        Info info = find(id);
        if (info != null) {
            info.setInfoStr(s);
            infoRepo.save(info);
            infoRepo.flush();
            log.info("Finish update info({})", id);
            TimeUnit.SECONDS.sleep(5);
        }
        //throw new Error();
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public void deadLock(long id1, String val1, long id2, String val2) throws InterruptedException {
        update(id1, val1);
        //Thread.sleep(3000);
        update(id2, val2);
    }

    public List<Info> listAll() {
        return infoRepo.findAll();
    }
}
