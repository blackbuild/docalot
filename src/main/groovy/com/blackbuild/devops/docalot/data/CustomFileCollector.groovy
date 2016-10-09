package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.Validation

/**
 * A file collector that is customizable by a closure.
 */
@Validation
class CustomFileCollector extends FileCollector {

    String extension

    Closure<Object> handler

    @Override
    protected Object parseFile(File file) {
        return handler.call(file)
    }
}
