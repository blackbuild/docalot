package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class CombinedXmlCollector extends InputStreamCollector {

    boolean validating = false
    boolean namespaceAware = true
    boolean allowDocTypeDeclaration = false

    @Override
    Object handleInput() {
        return new XmlSlurper(validating, namespaceAware, allowDocTypeDeclaration).parse(input)
    }
}
