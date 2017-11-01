package com.xiangbin.yang.study.spring.transcation.dao;

import com.xiangbin.yang.study.spring.transcation.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiangbin.yang
 * @since 2017/11/1
 */
public interface InfoRepo extends JpaRepository<Info, Long> {
}
