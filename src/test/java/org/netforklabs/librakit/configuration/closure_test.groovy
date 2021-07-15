package org.netforklabs.librakit.configuration

import groovy.transform.Internal

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
//class Server {
//    private String name
//    private String host
//    private String port
//
//    void name(String name) {
//        this.name = name
//    }
//
//    String getName() {
//        return name
//    }
//
//    void setName(String name) {
//        this.name = name
//    }
//
//    String getHost() {
//        return host
//    }
//
//    void setHost(String host) {
//        this.host = host
//    }
//
//    String getPort() {
//        return port
//    }
//
//    void setPort(String port) {
//        this.port = port
//    }
//
//    def leftShift(String name) {
//        this.name = name
//    }
//
//}
//
//
//
//def server(closure) {
//    Server serverObject = new Server()
//    closure.delegate = serverObject
//    closure()
//
//    println serverObject.name
//    println serverObject.host
//    println serverObject.port
//}
//
//server {
//    name "1234"
//}

def invokeMethod(String name, Object args) {
    println name
    args[0]()
}

__release__ {
    println "aaa"
}