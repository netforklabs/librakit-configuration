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

/* Create date: 2021/7/10. */

package org.netforklabs.librakit.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 持久化对象, 所有的配置信息都放在这个对象中
 *
 * @author fantexi
 */
public class SystemProperty {

    private static SystemProperty systemPropertyInstance;

    private final Map<String, Object> SYSTEM_PROPERTIES = new HashMap<>();

    private SystemProperty() {
    }

    public static SystemProperty getSystemProperty() {
        if (systemPropertyInstance == null)
            systemPropertyInstance = new SystemProperty();

        return systemPropertyInstance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key) {
        return (T) SYSTEM_PROPERTIES.get(key);
    }

    public void setProperty(String key, Object value) {
        SYSTEM_PROPERTIES.put(key, value);
    }

    public static <T> T GetProperty(String key) {
        return getSystemProperty().getProperty(key);
    }

    public static void SetProperty(String key, int value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, short value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, long value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, float value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, double value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, byte value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, boolean value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, char value) {
        getSystemProperty().setProperty(key, value);
    }

    public static void SetProperty(String key, Object value) {
        getSystemProperty().setProperty(key, value);
    }

}
