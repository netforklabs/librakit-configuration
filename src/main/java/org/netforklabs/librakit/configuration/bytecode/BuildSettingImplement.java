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

/**
 * @author fantexi
 */
public class BuildSettingImplement {

    static final ClassPool pool = ClassPool.getDefault();

    //
    // Setting的实现类
    //
    private final CtClass implement;

    public BuildSettingImplement(String classname) {
        this.implement = pool.makeClass(classname);
    }

    /**
     * 添加一个函数
     */
    public void addSetMethod(String returnTypeClassName, String methodName, String[] names)
            throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(pool.get(returnTypeClassName), methodName, pool.get(names), implement);
        ctMethod.setBody("{}");
    }

}
