typealias ActivityStateCmd<T> = (activity: T) -> Unit
typealias ActivityCommandConsumer<T> = (activity: T, cmds: List<ActivityStateCmd<T>>) -> Unit

fun <T> generalActivityCommandConsumer(activity: T, cmds: List<ActivityStateCmd<T>>) {
    cmds.forEach { cmd ->
        cmd(activity)
    }
}
