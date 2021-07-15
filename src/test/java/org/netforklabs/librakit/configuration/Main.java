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

package org.netforklabs.librakit.configuration;


import org.netforklabs.librakit.configuration.codeblock.Task;
import org.netforklabs.librakit.configuration.codeblock.TaskPool;

/**
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
public class Main {

    public static void main(String[] args) {
        MySetting implement = LibraKitConfigurationContext.getImplement(MySetting.class);

        System.out.println("task: nmb -----------------> ");
        Task nmb = TaskPool.getTask("nmb");
        nmb.execute((Object) new String[]{"123", "456"});

        System.out.println("task: start -----------------> ");
        Task start = TaskPool.getTask("start");
        start.execute("678", "890");

        System.out.println(implement.root().getName());
        System.out.println(implement.root().getAge());
        System.out.println(implement.root().getObjectName().getFnaem());

        System.out.println(implement.port());
        System.out.println(implement.args() == null);
    }

}
