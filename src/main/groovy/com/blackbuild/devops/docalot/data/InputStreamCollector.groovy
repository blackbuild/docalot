package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 *
 */
@DSL
abstract class InputStreamCollector extends CombinedCollector {

    InputStream input
}
