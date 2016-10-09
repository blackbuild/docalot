package com.blackbuild.devops.docalot.data

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification


class CollectorSpec extends Specification {

    @Rule TemporaryFolder temp = new TemporaryFolder()
    File dataFolder

    Collector collector

    def "properties collector"() {
        given:
        files([
                'info.properties': ['open=true', 'finished=false'],
                'info-env1.properties': ['status=disabled', 'age=1'],
                'info-env2.properties': ['status=alive', 'age=2', 'more=nothing'],
        ])

        when:
        createCollector(PropertiesCollector, "info") {}

        then:
        collector.global.open == 'true'
        collector.elements.env1.status == "disabled"
    }

    def "properties collector with post processing"() {
        given:
        files([
                'info.properties': ['open=true', 'finished=false'],
                'info-env1.properties': ['status=disabled', 'age=1'],
                'info-env2.properties': ['status=alive', 'age=2', 'more=nothing'],
        ])

        when:
        createCollector(PropertiesCollector, "info") {
            globalPostprocess { it.collectEntries { key, value -> ["global_$key" as String, value]}}
            elementPostprocess { it.collectEntries { key, value -> ["el_$key" as String, value]}}
        }

        then:
        collector.global.global_open == "true"
        collector.elements.env1.el_status == "disabled"
        collector.elements.env2.el_status == "alive"
    }

    def "info collector"() {
        given:
        files([
                'info.flag': [],
                'info-env1.flag': [],
        ])

        when:
        createCollector(FlagCollector, "info") {}

        then:
        collector.global
        collector.elements.env1
        !collector.elements.env2
    }

    void createCollector(@DelegatesTo.Target Class<? extends FileCollector> type, String domain, @DelegatesTo(genericTypeIndex = 0) Closure body) {
        collector = type.create(domain) {
            manualValidation(true) // since we inject the dataFolder, we must postpone validation to after applying the body
            sourceFolder dataFolder
        }.apply(body)
        collector.validate()
    }

    void files(Map<String, List<String>> content) {
        dataFolder = temp.newFolder()

        content.each { name, text ->
            new File(dataFolder, name).text = text.join('\n')
        }
    }
}