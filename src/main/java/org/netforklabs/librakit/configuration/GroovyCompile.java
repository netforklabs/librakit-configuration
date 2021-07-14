/*
 * Apache License.
 *
 * Copyright (c) 2021 Netforklabs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* Create date: 2021/7/11. */

package org.netforklabs.librakit.configuration;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.netforklabs.librakit.configuration.bytecode.ShellMethodDeclaring;
import java.util.Objects;

/**
 * Groovy脚本编译
 *
 * @author fantexi
 */
public class GroovyCompile {

    /**
     * 可以将外部变量传递给脚本内部使用
     */
    private static Binding groovyBinding             = null;

    /**
     * 脚本导入的包
     */
    private static StringBuilder imports            = null;

    /**
     * 所有函数声明的build
     */
    private static StringBuilder internal           = null;

    /**
     * 绑定编译脚本使用的变量, 下面多个类似方法都是一样的效果
     *
     * @param paramKey          变量名
     * @param paramValue        变量值
     */
    public static void bindVariable(String paramKey, Object paramValue) {
        ifNullCreateBinding();
        groovyBinding.setVariable(Objects.requireNonNull(paramKey), Objects.requireNonNull(paramValue));
    }

    /**
     * 导入外部的包, 提供给脚本使用
     *
     * @param __package 包名
     */
    public static void include(String __package) {
        ifNullCreateImports();
        imports.append("import ").append(__package).append("\n");
    }

    /**
     * 导入外部的类, 提供给脚本使用
     *
     * @param classname 类名
     * @param method    函数名
     */
    public static void includeStatic(String classname, String method) {
        include("static ".concat(classname).concat(".").concat(method));
    }

    /**
     * 添加一个自定义的函数
     *
     * @param text 函数体
     */
    public static void function(String text) {
        ifNullCreateInternal();
        internal.append(text).append("\n");
    }

    /**
     * 动态编译脚本
     * @param text 脚本内容
     */
    public static void compile(String text) {
        // groovy脚本解析并执行
        GroovyShell shell = new GroovyShell(groovyBinding);

        // 执行脚本
        System.out.println(String.valueOf(imports) + internal + text);
        shell.evaluate(String.valueOf(imports) + internal + text);

        clear();
    }

    //
    // 如果#groovyBinding是Null的话就创建一个
    //
    private static void ifNullCreateImports() {
        if(imports == null)
            imports = new StringBuilder();
    }

    //
    // 如果#imports是Null的话就创建一个
    //
    private static void ifNullCreateBinding() {
        if(groovyBinding == null)
            groovyBinding = new Binding();
    }

    //
    // 如果#internal是Null的话就创建一个
    //
    private static void ifNullCreateInternal() {
        if(internal == null)
            internal = new StringBuilder();
    }

    //
    // 清空所有当前创建的内容
    //
    private static void clear() {
        imports             = null;
        groovyBinding       = null;
        internal            = null;
    }

    /**
     * 添加函数声明
     *
     * @param call          调用者
     * @param declaring     函数声明
     */
    public static void addMethodDeclaring(String call, ShellMethodDeclaring declaring) {
        ifNullCreateInternal();
        internal.append(declaring.build(call)).append("\n");
    }

}
