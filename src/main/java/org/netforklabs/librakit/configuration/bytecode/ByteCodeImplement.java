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

/* Create date: 2021/7/10. */

package org.netforklabs.librakit.configuration.bytecode;

import javassist.*;
import org.netforklabs.librakit.configuration.SystemProperty;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fantexi
 */
public class ByteCodeImplement<I> {

    static final ClassPool pool = ClassPool.getDefault();

    static {
        pool.importPackage("org.netforklabs.librakit.configuration");
    }

    //
    // Setting的实现类
    //
    private CtClass implement;

    private final List<ShellMethodDeclaring> shellMethodDeclarings = new ArrayList<>();

    public ByteCodeImplement(String classname, Class<I> iface) {
        try {
            pool.insertClassPath(SystemProperty.class.getName());

            this.implement = pool.makeClass(classname);
            this.implement.setInterfaces(new CtClass[]{pool.get(iface.getName())});

            for (Method declaredMethod : iface.getDeclaredMethods()) {
                addSetMethod(declaredMethod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个函数
     */
    public void addSetMethod(Method method)
            throws NotFoundException, CannotCompileException {

        // 获取返回结果
        String methodName               = method.getName();
        String returnTypeClassName      = method.getReturnType().getName();

        System.out.println("returnType: " + returnTypeClassName);

        // set
        CtClass parameter = pool.get(returnTypeClassName);
        CtMethod setterMethod = new CtMethod(CtClass.voidType,
                methodName,
                new CtClass[]{parameter} ,
                implement);

        setterMethod.setBody("{ SystemProperty.SetProperty(\"" + methodName + "\", $1); }");

        // 构建脚本的Set函数声明
        ShellMethodDeclaring setterShellMethodDeclaring = new ShellMethodDeclaring();
        setterShellMethodDeclaring.setName(methodName);
        setterShellMethodDeclaring.setParameters(new String[]{parameter.getName()});
        setterShellMethodDeclaring.setBody("{ #call." + methodName + "(" + ShellMethodDeclaring.ALL_PARAMETERS + "); }");
        setterShellMethodDeclaring.setReturnType(ShellMethodDeclaring.R_VOID);

        // get
        CtClass returnType = pool.get(returnTypeClassName);
        CtMethod getterMethod = new CtMethod(returnType,
                methodName,
                null ,
                implement);

        getterMethod.setBody("{ return (" + returnType.getName() + ") SystemProperty.GetProperty(\"" + methodName + "\"); }");

        // 构建脚本的Get函数声明
        ShellMethodDeclaring getterShellMethodDeclaring = new ShellMethodDeclaring();
        getterShellMethodDeclaring.setName(methodName);
        getterShellMethodDeclaring.setBody("{ return #call." + methodName + "(); }");
        getterShellMethodDeclaring.setReturnType(returnType.getName());

        implement.addMethod(setterMethod);
        implement.addMethod(getterMethod);

        shellMethodDeclarings.add(setterShellMethodDeclaring);
        shellMethodDeclarings.add(getterShellMethodDeclaring);
    }

    @SuppressWarnings("unchecked")
    public I getInterfaceImplement() {
        I instance = null;
        try {
            Class<?> aClass = implement.toClass();
            instance = (I) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    /**
     * @return 所有函数声明
     */
    public List<ShellMethodDeclaring> getMethodDeclaring() {
        return shellMethodDeclarings;
    }

}
