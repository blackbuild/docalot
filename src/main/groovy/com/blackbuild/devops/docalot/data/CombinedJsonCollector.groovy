package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL
import groovy.json.JsonSlurper

@DSL
class CombinedJsonCollector extends InputStreamCollector {

    Map<String, String> slurperConfig

    @Override
    Object handleInput() {
        return new JsonSlurper(slurperConfig).parse(input)
    }
}
