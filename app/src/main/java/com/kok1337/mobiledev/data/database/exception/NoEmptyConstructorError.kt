package com.kok1337.mobiledev.data.database.exception

class NoEmptyConstructorError(clazz: Class<*>) :
    Error("${clazz.name} does not have an empty constructor")