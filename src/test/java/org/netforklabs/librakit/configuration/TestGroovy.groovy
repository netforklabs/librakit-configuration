//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

def task() {}

// 重要, 必须重写此方法
def invokeMethod(String name, Object args) {
    __internal_functions.processDsl(this, name, args)
}

task run {
   println "hello world"
}