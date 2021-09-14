package com.yusys.pipeline.utils

import groovy.json.JsonBuilder

class Data {

    def flowId
    def flowInstanceId
    def taskInstanceId
    def taskName
    def jenkinsJobBuildId
    def status

    Data(flowId, flowInstanceId, taskInstanceId, taskName, jenkinsJobBuildId, status) {
        this.flowId = flowId
        this.flowInstanceId = flowInstanceId
        this.taskInstanceId = taskInstanceId
        this.taskName = taskName
        this.jenkinsJobBuildId = jenkinsJobBuildId
        this.status = status
    }

    @NonCPS
    @Override
    String toString() {
        return new JsonBuilder(["flowId": flowId,
                "flowInstanceId": flowInstanceId,
                "taskInstanceId": taskInstanceId,
                "jenkinsJobBuildId": jenkinsJobBuildId,
                "taskName": taskName,
                "status": status]).toString()
    }
}