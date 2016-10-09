package com.blackbuild.devops.docalot.data

import com.blackbuild.groovy.configdsl.transform.DSL
import com.blackbuild.groovy.configdsl.transform.Validate
import groovy.io.FileType

import java.util.regex.Matcher

/**
 * A collector that handles multiple files. The extension of the files is defined by the collector, the name
 * for a global object is [domain].[extension], the name for environments is [domain]-[environment].[extension].
 *
 * Objects can optionally by post processed using the given closures.
 *
 */
@DSL
abstract class FileCollector extends Collector {

    @Validate
    File sourceFolder

    Closure<Object> globalPostprocess = Closure.IDENTITY
    Closure<Object> elementPostprocess = Closure.IDENTITY

    protected abstract String getExtension();

    @Override
    Object getGlobal() {
        File globalFile = new File(sourceFolder, "$domain.$extension")

        if (!globalFile.isFile()) return null

        return globalPostprocess.call(parseFile(globalFile))
    }

    @Override
    Map<String, Object> getElements() {

        Map<String, Object> result = [:]

        sourceFolder.eachFile(FileType.FILES) {
            Matcher m = it.name =~ ~/$domain-(.+)\.$extension/
            if (!m) return

            def envObject = elementPostprocess(parseFile(it))

            if (envObject)
                result[m[0][1]] = envObject
        }

        return result
    }

    protected abstract Object parseFile(File file)
}
