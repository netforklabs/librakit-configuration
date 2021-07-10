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

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author fantexi
 */
public class SettingImplement {

    static final ClassPool pool = ClassPool.getDefault();

    //
    // Setting的实现类
    //
    private final CtClass implement;

    public SettingImplement(String classname, Class<?> iface) {
        this.implement = pool.makeClass(classname);

        try {
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
        Class<?>[] parameterTypes       = method.getParameterTypes();
        CtClass[]  parameters           = new CtClass[parameterTypes.length];

        for(int i = 0; i < parameters.length; i++) {
            parameters[i] = pool.get(parameterTypes[i].getName());
        }

        CtMethod ctMethod = new CtMethod(pool.get(returnTypeClassName), toSetName(methodName), parameters, implement);
        ctMethod.setBody("{}");
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
