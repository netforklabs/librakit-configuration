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

//file:noinspection GroovyAssignabilityCheck
package org.netforklabs.librakit.configuration

import org.netforklabs.librakit.configuration.annotation.Task

def map(Map<String, String> map) { println map }
def list(List<String> list) { println list.toString() }

@Task def start(String[] args) {

}

@Task def run(String[] aa) {

}

@Task def names(String... args) {

}

map name: "zs", age: 18
list Arrays.asList(1, 2, 3, 4, 5)