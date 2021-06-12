package com.denglitong.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/11
 */
public class ScriptSample {

    public static void main(String[] args) throws Exception {
        // 获取 JavaScript 引擎，创建一个动态求和函数并调用
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        String scriptText = "function sum(a, b) { return a+b; }";
        engine.eval(scriptText);
        Invocable invocable = (Invocable)engine;
        Object result = invocable.invokeFunction("sum", 100, 99);
        System.out.println(result);
    }
}
