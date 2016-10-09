package com.blackbuild.devops.docalot.data

import com.blackbuild.devops.docalot.data.model.Dog
import com.blackbuild.devops.docalot.data.model.Person
import spock.lang.Specification

class ModelSpec extends Specification {

    Model model

    def "model dsl"() {
        expect:
        Model.create {
            domains {
                domain(PojoCollector, "info") {
                    sourceObject new Object()

                }
            }
        }

        Model.getMethod("domains", Closure)
    }


    def "create a model with globals and domains"() {
        when:
        model = Model.create {
            main(PojoCollector) {
                sourceObject Person.create {
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
                splitter { it.dogs }
            }

            domains {
                domain(PropertiesCollector, 'info') {
                    sourceFolder new File(ModelSpec.getResource("model").toURI())
                }
            }
        }

        then:
        model.global.name == "Klaus"
        model.global.info.mood == "happy"
        model.elements.Hasso.info.food == "cat"
    }



}
