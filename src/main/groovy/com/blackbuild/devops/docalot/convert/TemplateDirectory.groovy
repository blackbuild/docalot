package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Ignore
import com.blackbuild.groovy.configdsl.transform.Owner
import com.blackbuild.groovy.configdsl.transform.Validate

@DSL
abstract class TemplateDirectory {
    @Validate String source
    @Owner Converter converter
    String target

    @Ignore AntBuilder ant = new AntBuilder()
    @Ignore File targetDir
    @Ignore File sourceDir

    File getTargetDir() {
        if (!targetDir) {
            targetDir = new File(converter.outputDir, target ?: source.split("/")[-1])
            targetDir.mkdirs()
        }

        return targetDir
    }

    File getSourceDir() {
        if (!sourceDir)
            sourceDir = new File(converter.baseDir, source)
        return sourceDir
    }

    abstract void applyTemplates()
}
