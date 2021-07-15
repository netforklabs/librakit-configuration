package org.netforklabs.librakit.configuration


import org.netforklabs.librakit.configuration.annotation.NewInstance

class User
{
    private String name
    private int age

    @NewInstance
    private Friend friend

    Friend getFriend() {
        return friend
    }

    void setFriend(Friend friend) {
        this.friend = friend
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

}
