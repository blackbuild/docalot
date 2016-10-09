package com.blackbuild.devops.docalot.publish

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * Pushes the output to a single destination
 */
@DSL
abstract class Output {

    abstract void push(File directory)

}
