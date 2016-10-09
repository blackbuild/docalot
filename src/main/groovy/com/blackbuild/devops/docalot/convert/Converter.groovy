package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.*
import groovy.util.logging.Slf4j

/**
 * The converter is responsible for the convert step of the Docalot pipeline. It takes a global data object
 * and element objects as input, and applies the configured converters to it.
 */
@DSL
@Slf4j
@Validation(option=Validation.Option.VALIDATE_UNMARKED)
class Converter {

    File baseDir
    List<FileHandler> handlers = [new GroovyScriptFileHandler(), new GStringFileHandler(), new StaticHandler()]
    @Field(members = "directory") List<TemplateDirectory> directories

    @Ignore Object model
    @Ignore Map<String, Object> elements

    @Validate(Validate.Ignore)
    File outputDir

    def validate() {
        if (!outputDir)
            outputDir = File.createTempDir()
    }

    File run(Object model, Map<String, Object> elements) {
        this.model = model
        this.elements = elements

        directories.each { it.applyTemplates() }

        return outputDir
    }

    void handleSingleFile(File templateFile, File targetFolder, Map<String, Object> binding) {
        def handler = handlers.find { it.handles(templateFile) }
        def refinedBinding = binding + [config: this, model: model, elements: elements]

        handler?.applyFileTemplate(templateFile, targetFolder, refinedBinding)
    }




}
