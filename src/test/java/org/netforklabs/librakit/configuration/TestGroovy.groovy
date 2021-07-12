//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

import org.netforklabs.librakit.configuration.annotation.Task

def map(Map<String, String> map) { println map }
def list(List<String> list) { println list.toString() }

// 重要, 必须重写此方法
def invokeMethod(String name, Object args) {
    println name
    MarkFunction.__processDsl(this, name, args)
}

@Task def start() {

}

map name: "zs", age: 18
list Arrays.asList(1, 2, 3, 4, 5)