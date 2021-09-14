package com.yusys.pipeline.utils

import groovy.json.JsonBuilder

@groovy.transform.InheritConstructors
class EndData extends Data {

    def extraData = []

    def addExtraData(data) {
        this.extraData.add(data)
    }

    @NonCPS
    @Override
    String toString() {
        return new JsonBuilder(["flowId": flowId,
                "flowInstanceId": flowInstanceId,
                "taskInstanceId": taskInstanceId,
                "taskName": taskName,
                "taskJobBuildId": taskJobBuildId,
                "status": status,
                "extraData": extraData]).toString()
    }
}