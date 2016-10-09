package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Validate

/**
 * The PojoCollector provides the data from a single a Pojo
 */
@DSL
class PojoCollector extends CombinedCollector {

    @Validate
    Object sourceObject

    @Override
    protected Object handleInput() {
        return sourceObject
    }
}
