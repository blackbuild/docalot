package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class PropertiesCollector extends FileCollector {

    @Override
    protected String getExtension() {
        return "properties"
    }

    @Override
    protected Map<String, String> parseFile(File propertiesFile) {
        Properties result = new Properties()
        propertiesFile.withReader { result.load(it) }

        return [:] + result
    }

}
