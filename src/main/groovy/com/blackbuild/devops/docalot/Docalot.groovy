package com.blackbuild.devops.docalot

import com.blackbuild.devops.docalot.convert.Converter
import com.blackbuild.devops.docalot.data.Model
import com.blackbuild.devops.docalot.publish.Publisher
import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Validation
import groovy.util.logging.Slf4j
/**
 * Runnable configuration for Docalot. A docalot run is typically instantiated inside a groovy script.
 */
@DSL @Slf4j @Validation
class Docalot {

    Model gather

    Converter convert

    Publisher publish


    def execute() {

        def output = convert.run(gather.global, gather.elements)

        publish.run(output)
    }

    def static run(@DelegatesTo(Docalot) Closure body) {
        Docalot.create(body).execute()
    }


}






