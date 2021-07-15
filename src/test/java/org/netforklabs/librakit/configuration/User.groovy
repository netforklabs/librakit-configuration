package org.netforklabs.librakit.configuration

import org.netforklabs.librakit.configuration.annotation.Closure

class User
{
    private String name
    private int age

    private Name objectName;

    void name(String name) {
        setName(name)
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    int getAge() {
        return age
    }

    void setAge(int age) {
        this.age = age
    }

    Name getObjectName() {
        return objectName
    }

    void setObjectName(Name objectName) {
        this.objectName = objectName
    }
}
