package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL

/**
 * A combined collector creates globals and elements from a single object / file.
 *
 */
@DSL
abstract class CombinedCollector extends Collector {

    Closure<Object> selector = Closure.IDENTITY
    Closure<Map<String, Object>> splitter

    protected abstract Object handleInput();

    @Override
    Map<String, Object> getElements() {
        return splitter?.call(handleInput())
    }

    @Override
    Object getGlobal() {
        return selector?.call(handleInput())
    }
}
