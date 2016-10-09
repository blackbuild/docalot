package com.blackbuild.devops.docalot.data

import com.blackbuild.devops.docalot.data.model.Dog
import com.blackbuild.devops.docalot.data.model.Person
import spock.lang.Specification

class AggregatorSpec extends Specification {

    Aggregator<Person, Dog> data
    Person person

    def setup() {
        person = Person.create {
            name "Klaus"
            age 23
            dogs {
                dog("Hasso") {
                    race "Pudel"
                }
                dog("Bello") {
                    race "Husky"
                }
            }
        }

        data = new Aggregator(person, person.dogs)
    }


    def "basic test without stacks"() {
        expect:
        data.global.name == "Klaus"
        data.elements.Bello.race == "Husky"
    }

    def "info domain is not present by default"() {
        when:
        data.elements.Bello.info

        then:
        thrown(MissingPropertyException)
    }

    def "add domain without values"() {

        given:
        data.addElementDomain("info")

        when:
        def aValue = data.elements.Bello.info

        then:
        notThrown(MissingPropertyException)
        aValue == null
    }

    def "pass single value"() {
        given:
        data.addSingleElement("info", "Bello", [color: "white"])

        expect:
        person.dogs.Bello.info.color == "white"
        person.dogs.Bello.info.speed == null
        person.dogs.Hasso.info == null

    }


}


