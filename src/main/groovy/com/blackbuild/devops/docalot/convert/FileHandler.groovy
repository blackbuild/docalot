package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
abstract class FileHandler {

    abstract boolean handles(File template)

    abstract void applyFileTemplate(File template, File target, Map<String, Object> binding);

}
