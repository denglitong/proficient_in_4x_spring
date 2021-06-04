package com.denglitong.simple;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
public class Property {

    private String iDCode;

    private List list;

    private Set set;

    private Map map;

    // Map 的特例，kv 只能是 String
    private Properties properties;

    public String getiDCode() {
        return iDCode;
    }

    // Spring 现在已经能自动匹配 setIDCode() 或 setiDCode()
    // 来和 xml 中的 property.name=iDCode 自动注入
    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Property{" +
                "iDCode='" + iDCode + '\'' +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}
