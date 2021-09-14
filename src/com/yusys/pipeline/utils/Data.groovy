package com.yusys.pipeline.utils

import groovy.json.JsonBuilder

class Data {

    def flowId
    def flowInstanceId
    def taskInstanceId
    def taskName
    def taskJobBuildId
    def status

    Data(flowId, flowInstanceId, taskInstanceId, taskName, taskJobBuildId, status) {
        this.flowId = flowId
        this.flowInstanceId = flowInstanceId
        this.taskInstanceId = taskInstanceId
        this.taskName = taskName
        this.taskJobBuildId = taskJobBuildId
        this.status = status
    }

    @NonCPS
    @Override
    String toString() {
        return new JsonBuilder(["flowId": flowId,
                "flowInstanceId": flowInstanceId,
                "taskInstanceId": taskInstanceId,
                "taskJobBuildId": taskJobBuildId,
                "taskName": taskName,
                "status": status]).toString()
    }
}