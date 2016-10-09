package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class Splitted extends Single {

    Closure<Map<String, Object>> selectElements

    @Override
    void applyTemplates() {

        Map<String, Object> directories = selectElements ? selectElements.call(converter.model) : converter.elements

        directories.each { dirname, element ->
            File dir = new File(targetDir, dirname)

            applyTemplatesFromSingleDir(dir, [element: element])
        }
    }
}
