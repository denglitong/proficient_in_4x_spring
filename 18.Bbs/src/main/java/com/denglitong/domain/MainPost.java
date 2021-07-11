package com.denglitong.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * 通过 @Inheritance 指定了 PO 映射继承关系
 * - InheritanceType.TABLE_PER_CLASS: 每个类一张表
 * - InheritanceType.JOINED: 连接的子类一张表
 * - InheritanceType.SINGLE_TABLE: 每个类层次结构一张表
 * 通过 @DiscriminatorColumn 指定了 PO 映射继承关系 用来辨别该类的辨别符列
 * 通过 @DiscriminatorValue 指定了用来编写该类的值
 *
 * @author litong.deng@foxmail.com
 * @date 2021/7/1
 */
@Entity
@Table(name = "t_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("2")
public class MainPost extends Post {
    
}
