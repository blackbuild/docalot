package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class StaticHandler extends FileHandler {
    @Override
    boolean handles(File template) {
        return true
    }

    @Override
    void applyFileTemplate(File template, File target, Map<String, Object> binding) {
        def srcStream = template.newDataInputStream()
        def dstStream = new File(target, template.name).newDataOutputStream()
        dstStream << srcStream
        srcStream.close()
        dstStream.close()
    }
}
