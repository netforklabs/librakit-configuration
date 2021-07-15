import org.netforklabs.librakit.configuration.Friend
import org.netforklabs.librakit.configuration.User

def root(closure) {
    def user = new User()
    user.friend = new Friend()
    closure.delegate = user

    closure()

    println user.name
    println user.getFriend().name
}

root {
    name = "zs"
    friend.name = "aaaa"
}