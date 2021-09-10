package com.yusys.libs

import groovy.json.JsonBuilder

class Data {

    def flowId
    def flowInstanceId
    def taskInstanceId
    def taskName
    def status
    def extraData = []

    Data(flowId, flowInstanceId, taskInstanceId, taskName, status) {
        this.flowId = flowId
        this.flowInstanceId = flowInstanceId
        this.taskInstanceId = taskInstanceId
        this.taskName = taskName
        this.status = status
    }

    def addExtraData(extraData) {
        this.extraData.add(extraData)
    }

    @NonCPS
    @Override
    String toString() {
        return new JsonBuilder(["flowId": flowId,
                "flowInstanceId": flowInstanceId,
                "taskInstanceId": taskInstanceId,
                "taskName": taskName,
                "status": status,
                "extraData": extraData]).toString()
    }
}