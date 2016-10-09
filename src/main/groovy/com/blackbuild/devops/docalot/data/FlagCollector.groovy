package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class FlagCollector extends FileCollector {

    @Override
    protected String getExtension() {
        return "flag"
    }

    @Override
    protected Boolean parseFile(File flag) {
        return true
    }

}
