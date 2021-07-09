//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

import static org.netforklabs.librakit.configuration.LibraKits.invokeMethod
import static org.netforklabs.librakit.configuration.LibraKits.getTaskPool

def task() {}

def invokeMethod(String name, Object args) {
    invokeMethod(this, name, args)
}

task run {
   println "hello world"
}

taskPool.getTask("run")