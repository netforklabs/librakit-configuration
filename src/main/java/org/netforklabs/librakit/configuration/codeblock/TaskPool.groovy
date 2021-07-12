package org.netforklabs.librakit.configuration.codeblock
/**
 * 任务容器, 负责管理所有配置中编写的任务
 *
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
class TaskPool {

    private static TaskPool pool = new TaskPool()

    // map
    private Map<String, Task> tasks = new HashMap<>()

    /**
     * 提交一个任务到Map
     *
     * @param name 任务名称
     * @param task 任务实例对象
     */
    void pushTask0(Task task) {
        tasks.put(task.name, task)
    }

    /**
     * 获取一个任务对象
     *
     * @param name 任务名称
     */
    Task getTask0(String name) { tasks[name] }

    static Task pushTask(Task task) {
        return pool.pushTask0(task)
    }

    static Task getTask(String name) {
        return pool.getTask0(name)
    }

    /**
     * 获取任务函数对象
     * @param object 对象内容
     */
    static void getTasks(Object object) {
        Class<?> aClass = object.class
        aClass.declaredMethods.each {
            if(it.isAnnotationPresent(org.netforklabs.librakit.configuration.annotation.Task.class)) {
                pushTask(new Task(name: it.name, method: it, object: object))
            }
        }
    }

}