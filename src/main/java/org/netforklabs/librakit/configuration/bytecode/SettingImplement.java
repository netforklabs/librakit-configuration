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
import java.nio.charset.StandardCharsets;

/**
 * @author fantexi
 */
public class SettingImplement<I> {

    static final ClassPool pool = ClassPool.getDefault();

    static {
        pool.importPackage("org.netforklabs.librakit.configuration");
    }

    //
    // Setting的实现类
    //
    private CtClass implement;

    public SettingImplement(String classname, Class<I> iface) {
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

        // set
        CtMethod setterMethod = new CtMethod(CtClass.voidType,
                toSetName(methodName),
                new CtClass[]{pool.get(returnTypeClassName)} ,
                implement);

        setterMethod.setBody("{ SystemProperty.SetProperty(\"" + methodName + "\", $1); }");

        // get
        CtClass returnType = pool.get(returnTypeClassName);
        CtMethod getterMethod = new CtMethod(returnType,
                methodName,
                null ,
                implement);

        getterMethod.setBody("{ return (" + returnType.getName() + ") SystemProperty.GetProperty(\"" + methodName + "\"); }");

        implement.addMethod(setterMethod);
        implement.addMethod(getterMethod);

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
     * 将方法名的首字母转大写并添加上set前缀
     */
    private static String toSetName(String name) {
        byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
        bytes[0] = (byte) (bytes[0] - 32);
        return "set".concat(new String(bytes));
    }

}
