package com.xiangbin.yang.study.spring.transcation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiangbin.yang
 * @since 2017/11/1
 */
@Data
@NoArgsConstructor
@Entity
public class Info {
    @GeneratedValue
    @Id
    private long id;

    private String infoStr;

    public Info(String info) {
        this.infoStr = info;
    }
}
