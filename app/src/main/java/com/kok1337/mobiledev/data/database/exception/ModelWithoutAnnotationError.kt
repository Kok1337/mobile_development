package com.kok1337.mobiledev.data.database.exception

class ModelWithoutAnnotationError(clazz: Class<*>) :
    Error("${clazz.name} does not have an annotation @Entity")