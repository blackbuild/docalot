package com.blackbuild.devops.docalot.publish

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * Handles the publishing of the conversion results to a custom destination.
 */
@DSL
class Publisher {

    List<Output> outputs

    void run(File file) {
        outputs.each { it.push(file) }
    }
}
