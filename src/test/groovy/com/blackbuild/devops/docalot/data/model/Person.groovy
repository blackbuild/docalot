package com.blackbuild.devops.docalot.data.model

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Key

@DSL
class Person {
    String name
    int age
    Map<String, Dog> dogs
}

@DSL
class Dog {
    @Key String name
    String race
}
