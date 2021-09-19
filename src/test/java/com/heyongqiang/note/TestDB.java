package com.heyongqiang.note;

import cn.hutool.db.DbUtil;
import com.heyongqiang.note.util.dbUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class TestDB {
    //使用日志工厂类 记录日志
    private Logger logger = LoggerFactory.getLogger(TestDB.class);

    /**
     * 单元测试方法
     *  1.方法的返回值 建议使用void 一般没有返回值
     *  2.参数列表建议空参 一般是没有参数
     *  3.方法上需要设置@Test注解
     *  4.每个方法都能独立运行
     *
     *  判定
     */
    @Test
    public void testDb(){
        System.out.println(dbUtil.getConnection());
        //使用日志
        logger.debug("debug:" + dbUtil.getConnection());
        //logger.info("获取数据库链接" + dbUtil.getConnection());
        logger.info("获取数据库链接 : {}" ,dbUtil.getConnection());
    }
    public void testDbproject(){
        System.out.println(dbUtil.getConnection());

    }


}
