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

/* Create date: 2021/7/14. */

package org.netforklabs.librakit.configuration.bytecode;

import javassist.*;
import org.netforklabs.librakit.configuration.Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 代理类, 方便生成一些函数
 *
 * @author fantexi
 */
public class ProxyClass {

    private final CtClass declaring;

    private final Object proxyObject;

    private static final String PROXY_OBJECT_NAME = "proxyObject";

    private static final String SET_PROXY_OBJECT = "setProxyObject";

    private final String CALL;

    public ProxyClass(Object proxyObject) throws Exception {
        this.proxyObject = proxyObject;
        this.declaring = ClassPool.makeClass("org.netforklabs.librakit.conf.iguration.bytecode.$LibraKitProxyClass" + proxyObject.getClass().getSimpleName());
        this.declaring.addField(new CtField(ClassPool.OBJECT, PROXY_OBJECT_NAME, declaring));

        CALL = "((" + proxyObject.getClass().getName() + ")" + PROXY_OBJECT_NAME + ").";

        Class<?> proxyObjectClass = this.proxyObject.getClass();
        initProxyClass(proxyObjectClass);
        synchronizeFieldMethod(proxyObjectClass);

        addProxyMethod(ClassPool.VOID,"setProxyObject",
                new CtClass[]{ClassPool.get(Object.class.getName())},
                "{ this." + PROXY_OBJECT_NAME + " = $1; }");

        addProxyMethod(ClassPool.get(Object.class.getName()),
                "getObject",
                ClassPool.NO_PARAMETER,
                "{ return (" + proxyObject.getClass().getName() + ") this." + PROXY_OBJECT_NAME + "; }");
    }

    /**
     * 初始化所有代理类的函数
     */
    private void initProxyClass(Class<?> aClass) throws Exception {
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method: declaredMethods) {

            if(Modifier.isPrivate(method.getModifiers())) {
                continue;
            }

            String  name                    = method.getName();
            Class<?>[] parameterTypes       = method.getParameterTypes();
            CtClass returnType              = ClassPool.get(method.getReturnType().getName());

            StringBuilder commands          = new StringBuilder();
            CtClass[] params                = ClassPool.NO_PARAMETER;
            if(parameterTypes.length > 0) {
                params = new CtClass[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    params[i] = ClassPool.get(parameterTypes[i].getName());
                    commands.append("$").append(i + 1).append(",");
                }

                int cleng = commands.length();
                commands.delete(cleng - 1, cleng);
            }

            addProxyMethod(returnType, name, params, isReturnBody(returnType != ClassPool.VOID,
                    CALL + name + "(" + commands + ");"));
        }
    }

    private void synchronizeFieldMethod(Class<?> aClass) throws NotFoundException {
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();

            if(!Util.letter(0, name))
                continue;

            addProxyMethod(ClassPool.VOID,
                    name,
                    new CtClass[]{ClassPool.get(declaredField.getType().getName())},
                    "{" + CALL + "set" + Util.firstLetterUppercase(declaredField.getName()) + "($1);}");
        }
    }

    /**
     * 设置代理函数
     */
    public void addProxyMethod(CtClass returnType, String name, CtClass[] params, String body) {
        try {
            CtMethod method = new CtMethod(returnType, name, params, declaring);
            method.setBody(body);

            declaring.addMethod(method);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    private String isReturnBody(boolean isReturn, String body) {
        return isReturn ? "{return " + body + "}" : "{" + body + "}";
    }

    /**
     * 创建实例对象
     */
    public Object newInstance() throws Exception {
        Class<?> aClass = declaring.toClass();
        Object instance = aClass.newInstance();

        Method setProxyObject = aClass.getDeclaredMethod(SET_PROXY_OBJECT, Object.class);
        setProxyObject.invoke(instance, proxyObject);

        return instance;
    }

}
