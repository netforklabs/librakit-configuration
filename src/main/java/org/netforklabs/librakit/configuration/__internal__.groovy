//file:noinspection GroovyAssignabilityCheck

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

/* Create date: 2021/7/9. */

package org.netforklabs.librakit.configuration

/**
 * 配置文件的内置的函数
 * 
 * @author fantexi
 */
class __internal_functions {

    /**
     * 调用的函数名称
     */
    private static var argv_name

    /**
     * 调用的函数闭包对象
     */
    private static var argv_closure

    /**
     * 任务容器
     */
    private static var pool = new TaskPool()

    //
    // 任务以及其他代码块的DSL处理
    //
    static void processDsl(Object current, String name, Object args) {
        if(name == "task") {
            pool.pushTask(new Task(name: argv_name, closure: argv_closure))
            clearArgv()
        } else {
            argv_name = name
            if(args[0] instanceof Closure)
                argv_closure = args[0]
        }
    }

    private static void clearArgv() {
        argv_name = null
        argv_closure = null
    }

    static TaskPool getTaskPool() { pool }

}

/**
 * 配置文件的内置的属性对象
 *
 * @author fantexi
 */
class InternalProperty {

}