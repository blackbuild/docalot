package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

@DSL
class Model {

    Collector main
    List<Collector> domains
    Aggregator $data

    Collector main(@DelegatesTo.Target Class<? extends Collector> type, @DelegatesTo Closure body) {
        main type.create("", body)
    }

    Object getGlobal() {
        prepareData()
        $data.global
    }

    Map<String, Object> getElements() {
        prepareData()
        $data.elements
    }

    void prepareData() {
        if ($data) return

        $data = new Aggregator(main.global, main.elements)

        domains.each { collector ->
            $data.add(collector.domain, collector.global)
            $data.addElements(collector.domain, collector.elements)
        }
    }
}
