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

/* Create date: 2021/7/14. */

package org.netforklabs.librakit.configuration;

/**
 * @author fantexi
 */
public class Util {

    public static boolean letter(int i, String str) {
        byte[] bytes = str.getBytes();
        char c = (char) bytes[0];
        return ('a' <= c && 'z' >= c) || ('A' <= c && 'Z' >= c);
    }

    public static String firstLetterUppercase(String str) {
        byte[] bytes = str.getBytes();
        bytes[0] = (byte) (bytes[0] - 32);

        return new String(bytes);
    }

}
