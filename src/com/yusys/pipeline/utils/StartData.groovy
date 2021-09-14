package com.yusys.pipeline.utils

import groovy.json.JsonBuilder

class StartData extends Data {

    def buildUrl

    StartData(flowId, flowInstanceId, taskInstanceId, taskName, taskJobBuildId, buildUrl) {
        super(flowId, flowInstanceId, taskInstanceId, taskName, taskJobBuildId, "started")
        this.buildUrl = buildUrl
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
                "buildUrl": buildUrl]).toString()
    }
}