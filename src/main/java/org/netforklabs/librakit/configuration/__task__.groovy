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
 * 脚本任务
 *
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
class Task {

    /**
     * 任务名称
     */
    String name

    /**
     * 闭包实例
     */
    Closure closure

    /**
     * 执行闭包函数, 并返回任务数据内容。甚至是Void
     */
    def execute() { closure.call() }

    /**
     * 执行带参数的闭包函数, 并返回任务数据内容。
     */
    def execute(Object... args) { closure.call(args) }

}

/**
 * 任务容器, 负责管理所有配置中编写的任务
 *
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
class TaskPool {

    // map
    private Map<String, Task> tasks = new HashMap<>()

    /**
     * 提交一个任务到Map
     *
     * @param name 任务名称
     * @param task 任务实例对象
     */
    void pushTask(Task task) {
        tasks.put(task.name, task)
    }

    /**
     * 获取一个任务对象
     *
     * @param name 任务名称
     */
    Task getTask(String name) { tasks[name] }

}
