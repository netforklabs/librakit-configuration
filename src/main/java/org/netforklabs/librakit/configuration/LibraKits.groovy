//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

class LibraKits {

    private static def argv

    private static def pool = new TaskPool()

    static def invokeMethod(Object current, String name, Object args) {
        if(name == "task") {
            pool.pushTask(name, argv)
            argv = null
        } else {
            if(args[0] instanceof Closure)
                argv = args[0]
        }
    }

    static def getTaskPool() { pool }

}
