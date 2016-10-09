package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class Static extends TemplateDirectory {

    @Override
    void applyTemplates() {
        ant.copy(todir: targetDir) {
            fileset(dir: getSourceDir())
        }
    }

}
