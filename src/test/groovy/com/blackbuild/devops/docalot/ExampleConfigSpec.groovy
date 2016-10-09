package com.blackbuild.devops.docalot

import spock.lang.Specification

class ExampleConfigSpec extends Specification {

    def "read Example Config"() {
        when:
        Docalot.createFromScript(ExampleConfig)

        then:
        noExceptionThrown()


    }


}
