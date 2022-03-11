package com.kok1337.mobiledev.data.database.entitymapper.exception

import java.lang.reflect.Field

class NullColumnError(field: Field) : Error("For ${field.name} Column is null")