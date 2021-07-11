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

package org.netforklabs.librakit.configuration

import org.netforklabs.librakit.configuration.bytecode.SettingImplement
import org.netforklabs.librakit.configuration.iface.Alias
import org.netforklabs.librakit.configuration.iface.Setting

/**
 * @author fantexi
 */
class LibraKitConfigurationContext {

    private static final String DEFAULT_CONFIG_NAME = "application.librakit"

    //
    // 1. 判断Setting的集成接口是否存在@Alias注解
    //
    // 2. 将生成Setting接口的实现类
    //
    // 3. 解析脚本
    //
    static <T> T getImplement(Class<T> settingClass)
    {
        if(settingClass == null)
            throw new ClassNotFoundException("找不到Setting的继承接口")

        def configName = DEFAULT_CONFIG_NAME

        // #1
        if(settingClass.isAnnotationPresent(Alias.class))
        {
            var alias = settingClass.getDeclaredAnnotation(Alias.class)
            configName = alias.value()
        }

        // #2
        var implement = new SettingImplement<T>(
                "org.netforklabs.librakit.configuration.Implement",
                settingClass)

        return implement.getInterfaceImplement()
    }

}
