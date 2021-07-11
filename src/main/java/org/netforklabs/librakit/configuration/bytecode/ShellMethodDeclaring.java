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

/* Create date: 2021/7/11. */

package org.netforklabs.librakit.configuration.bytecode;

/**
 * @author fantexi
 */
public class ShellMethodDeclaring {

    /**
     * 函数名称
     */
    private String name;

    /**
     * 函数内容
     */
    private String body;

    /**
     * 参数列表
     */
    private String[] parameters;

    /**
     * 返回类型
     */
    private String returnType;

    public static final String R_VOID               = "void";
    public static final String R_INTEGER            = "java.lang.Integer";
    public static final String R_SHORT              = "java.lang.Short";
    public static final String R_LONG               = "java.lang.Long";
    public static final String R_BOOL               = "java.lang.Boolean";
    public static final String R_FLOAT              = "java.lang.Float";
    public static final String R_DOUBLE             = "java.lang.Double";
    public static final String R_BYTE               = "java.lang.Byte";
    public static final String R_STRING             = "java.lang.String";

    /**
     * 传入全部参数
     */
    public static final String ALL_PARAMETERS       = "#ps";

    /**
     * 构建函数
     */
    public String build(String call) {
        final StringBuilder builder = new StringBuilder("public ");
        builder.append(returnType).append(" ").append(name);
        builder.append("(");

        if(parameters != null) {
            int count = 0;
            final StringBuilder buildParam = new StringBuilder();
            for (String parameter : parameters) {
                // 构建参数
                builder.append(parameter).append(" var").append(count).append(",");
                buildParam.append("var").append(count).append(",");
                count++;
            }

            // 删除最后一个逗号
            int length = builder.length();
            builder.delete(length - 1, length);

            length = buildParam.length();
            buildParam.delete(length - 1, length);

            if(body.contains(ALL_PARAMETERS)) {
                body = body.replaceAll(ALL_PARAMETERS, buildParam.toString());
            }

        }

        builder.append(")");

        if(call != null) {
            body = body.replaceAll("#call", call);
        }

        builder.append(body);
        return builder.toString();
    }

    /* -------------------------------------- Get/Set -------------------------------------- */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

}
