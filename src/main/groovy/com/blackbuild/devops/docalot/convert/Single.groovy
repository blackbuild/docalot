package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL
import groovy.io.FileType

@DSL
class Single extends TemplateDirectory {
    @Override
    void applyTemplates() {
        applyTemplatesFromSingleDir(targetDir)
    }

    void applyTemplatesFromSingleDir(File target, Map<String, Object> bindings = [:]) {
        sourceDir.eachFileRecurse(FileType.FILES) { template ->
            String templateRelativeFolderName = template.parentFile.absolutePath - sourceDir.absolutePath
            File targetFolder = new File(target, templateRelativeFolderName)
            targetFolder.mkdirs()
            converter.handleSingleFile(template, targetFolder, bindings)
        }
    }

}
