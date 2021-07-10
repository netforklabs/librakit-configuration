//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

def map(Map<String, String> map) {}

def task() {}

// 重要, 必须重写此方法
def invokeMethod(String name, Object args) {
    InternalFunctions.processDsl(this, name, args)
}

task run {
   println "hello world"
}

InternalFunctions.taskPool.getTask("run").execute()