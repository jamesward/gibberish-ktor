import java.lang.ProcessHandle

actual fun getTimeMillis(): Long {
    return System.currentTimeMillis()
}

actual fun getStartup(): Long {
    return ProcessHandle.current().info().startInstant().get().toEpochMilli()
}