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


import java.util.HashMap;
import java.util.Map;

/**
 * @author fantexi
 */
@SuppressWarnings("JavaDoc")
public class Main {

    public static void main(String[] args) {
        MySetting implement = LibraKitConfigurationContext.getImplement(MySetting.class);

        TaskPool.getTask("nop").execute((Object) null);
        TaskPool.getTask("start").execute();

        System.out.println(implement.port());
        System.out.println(implement.args() == null);
    }

}
