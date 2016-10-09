package com.blackbuild.devops.docalot.data

import groovy.util.logging.Slf4j

/**
 * A data collector handles the incremental creation of the model. It provides helper methods to include addtional
 * domain data by directly using the meta class.
 * @param <G> The class of the global object
 * @param <E> The class of the element objects
 */
@Slf4j
class Aggregator<G, E> {

    G global
    Map<String, E> elements = [:]

    Set<String> globalDomains = []
    Set<String> elementDomains = []

    /**
     * Create a new Aggregator using the provided main and element models. Note that when using a config dsl object
     * as main model, elements will mostly be simply a member of the main object (i.e.
     * <code>new Aggregator(mySystem, mySystem.environments)</code>).
     *
     * @param object The main object of the data.
     * @param elements The actual elements.
     */
    Aggregator(G object, Map<String, E> elements = [:]) {
        global = object

        this.elements = [:] + elements
    }

    /**
     * A domain information to the main model. This is done by adding the provided object directly as a new dynamic
     * property to the main model (using metaClass).
     * @param domain The domain of the added object.
     * @param value The actual information object.
     */
    void add(String domain, Object value) {
        global.metaClass."$domain" = value
        globalDomains << domain
    }

    void addElements(String domain, Map<String, Object> values) {
        addElementDomain(domain)

        values.each { element, value ->
            elements[element]."$domain" = value
        }
    }

    void addSingleElement(String domain, String name, Object value) {
        addElements(domain, [(name):value])
    }

    void addElementDomain(String domain) {
        if (elementDomains.contains(domain)) return

        elements.each { name, element ->
            element.metaClass."$domain" = null
        }
    }
}
