package com.blackbuild.devops.docalot

import com.blackbuild.devops.docalot.convert.Single
import com.blackbuild.devops.docalot.convert.Splitted
import com.blackbuild.devops.docalot.convert.Static
import com.blackbuild.devops.docalot.data.ModelSpec
import com.blackbuild.devops.docalot.data.PojoCollector
import com.blackbuild.devops.docalot.data.PropertiesCollector
import com.blackbuild.devops.docalot.data.model.Person
import com.blackbuild.devops.docalot.publish.DirectoryOutput

def mainData = Person.create {
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

Docalot.create {

    gather {
        main(PojoCollector) {
            sourceObject mainData
            splitter { it.dogs }
        }

        domain(PropertiesCollector, 'info') {
            sourceFolder new File(ModelSpec.getResource("model").toURI())
        }

        // verify {}
    }

    convert {
        baseDir new File("docs")

        directory(Single) {
            source "index"
            target "."
        }
        directory(Splitted) {
            source "branches"
            selectElements { Person person -> system.branches.collectEntries { [it.value.simpleName, it.value]} }
        }
        directory(Splitted) {
            source "components"
            selectElements { Person person -> system.components.collectEntries { [it.value.name, it.value]} }
        }
        directory(Static) {
            source "css"
        }
    }

    publish {
        output(DirectoryOutput) {
            target new File("output")
        }
    }

}

