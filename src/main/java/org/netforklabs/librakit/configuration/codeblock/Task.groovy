/*
 * Apache License.
 *
 * Copyright (c) 2021 Netforklabs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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

/* Create date: 2021/7/12. */
//file:noinspection GroovyAssignabilityCheck

package org.netforklabs.librakit.configuration.codeblock

import java.lang.reflect.Method

/**
 * 任务实体对象
 *
 * @author fantexi
 */
@SuppressWarnings("UnusedReturnValue")
class Task {

    /**
     * 任务名称
     */
    private String name

    /**
     * 闭包实例
     */
    private Closure closure

    /**
     * 执行闭包函数, 并返回任务数据内容。甚至是Void
     */
    Object execute() {
        closure()
    }

    /**
     * 执行带参数的闭包函数, 并返回任务数据内容。
     */
    def execute(Object... args) {
        closure(args)
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Method getMethod() {
        return method
    }

    void setMethod(Method method) {
        this.method = method
    }

}
