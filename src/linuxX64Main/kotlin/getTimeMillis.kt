actual fun getTimeMillis(): Long {
    return kotlin.system.getTimeMillis()
}

actual fun getStartup(): Long {
    return getTimeMillis()
}