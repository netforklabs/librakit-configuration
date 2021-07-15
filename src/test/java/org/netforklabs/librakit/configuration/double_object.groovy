import org.netforklabs.librakit.configuration.Name
import org.netforklabs.librakit.configuration.User

def root(closure) {
    def user = new User()
    user.objectName = new Name()
    closure.delegate = user

    closure()

    println user.name
    println user.getObjectName().fnaem
}

root {
    name = "zs"
    objectName.fnaem = "aaaa"
}