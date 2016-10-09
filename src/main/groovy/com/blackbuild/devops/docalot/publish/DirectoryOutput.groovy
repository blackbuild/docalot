package com.blackbuild.devops.docalot.publish

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Validate

/**
 * Copies the output into a target directory
 */
@DSL
class DirectoryOutput extends Output {

    AntBuilder $ant = new AntBuilder()

    @Validate File target
    boolean clean = true

    @Override
    void push(File directory) {

        if (clean)
            $ant.delete(dir: target)

        $ant.copy(todir: target) {
            fileset(dir: directory)
        }
    }
}
