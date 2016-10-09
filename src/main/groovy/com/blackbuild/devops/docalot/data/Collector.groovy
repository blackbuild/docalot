package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Key

/**
 * A collector is responsible for reading and parsing model information.
 */
@DSL
abstract class Collector {

    @Key String domain

    abstract Object getGlobal();

    abstract Map<String, Object> getElements()

}
