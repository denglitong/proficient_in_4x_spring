package com.denglitong.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/4
 */
@Component
public class MyComponent {

    @Autowired(required = false)
    @Lazy
    private List<Plugin> plugins;

    @Autowired
    private Map<String, Plugin> pluginMaps;

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    public Map<String, Plugin> getPluginMaps() {
        return pluginMaps;
    }

    public void setPluginMaps(Map<String, Plugin> pluginMaps) {
        this.pluginMaps = pluginMaps;
    }

    @Override
    public String toString() {
        return "MyComponent{" +
                "plugins=" + plugins +
                ", pluginMaps=" + pluginMaps +
                '}';
    }
}
