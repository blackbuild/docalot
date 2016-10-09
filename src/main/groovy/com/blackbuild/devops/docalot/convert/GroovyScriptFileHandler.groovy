package com.blackbuild.devops.docalot.convert

import com.blackbuild.groovy.configdsl.transform.DSL
import groovy.util.logging.Slf4j
import groovy.xml.MarkupBuilder

@DSL
@Slf4j
class GroovyScriptFileHandler extends FileHandler {

    final GroovyShell shell = new GroovyShell()

    Class<? extends MarkupBuilder> builderType = MarkupBuilder

    @Override
    boolean handles(File template) {
        return template.name.endsWith('.groovy')
    }

    @Override
    void applyFileTemplate(File template, File target, Map<String, Object> binding) {
        def script = shell.parse(template)
        script.binding.variables.putAll(binding)

        def out = new StringWriter()
        script.binding.out = out
        script.binding.builder = builderType.newInstance(out)

        def result = script.run()

        def outputFile = new File(target, template.name - ".groovy")
        if (out.buffer.length()) {
            outputFile.text = out.toString()
            log.info("Using content 'out' variable as result for $template")
            log.debug(out.toString()[0..20])
        } else if (result) {
            outputFile.text = result.toString()
            log.info("Using result of type '${result.getClass().simpleName}' as output of $template")
        } else {
            log.info("Ignoring result of template '$template'")
        }
    }
}
