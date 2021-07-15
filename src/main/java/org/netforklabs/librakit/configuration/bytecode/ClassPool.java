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

import javassist.CtClass;
import javassist.NotFoundException;

/**
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
public class ClassPool {

    private static final javassist.ClassPool classPool = javassist.ClassPool.getDefault();

    public static CtClass OBJECT;

    public static CtClass VOID = CtClass.voidType;

    public static final CtClass[] NO_PARAMETER = new CtClass[]{};

    static {
        try {
            OBJECT = classPool.get(Object.class.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public static CtClass get(String name) throws NotFoundException {
        return classPool.get(name);
    }


    public static void importPackage(String ipackage) {
        classPool.importPackage(ipackage);
    }

    public static void insertClassPath(String classpath) throws NotFoundException {
        classPool.insertClassPath(classpath);
    }

    public static CtClass makeClass(String classname) {
        return classPool.makeClass(classname);
    }
}
