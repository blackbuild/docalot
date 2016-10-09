package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL
import groovy.text.GStringTemplateEngine

@DSL
class GStringFileHandler extends FileHandler {

    final GStringTemplateEngine engine = new GStringTemplateEngine()

    @Override
    boolean handles(File template) {
        return true
    }

    @Override
    void applyFileTemplate(File template, File targetFolder, Map<String, Object> binding) {
        new File(targetFolder, template.name).setText(
                engine.createTemplate(template.newReader('UTF-8')).make(binding).toString(), 'UTF-8')
    }
}
