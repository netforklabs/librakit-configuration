//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration
import static org.netforklabs.librakit.configuration.iface.Setting.task

def map(Map<String, String> map) {}

// 重要, 必须重写此方法
def invokeMethod(String name, Object args) {
    MarkFunction.__processDsl(this, name, args)
}

task run {
   println "hello world"
}

TaskPool.getTask("run").execute()