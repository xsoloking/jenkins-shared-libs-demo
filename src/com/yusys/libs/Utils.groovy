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

class MetaData {
    def name
    def value

    MetaData(name, value) {
        this.name = name
        this.value = value
    }

    @NonCPS
    @Override
    String toString() {
        return ["name": name, "value": value].toString()
    }
}

class GitRepoMetaData {

    def commitId
    def commitSubject
    def commitDate
    def commitAuthor

    GitRepoMetaData(commitId, commitSubject, commitDate, commitAuthor){
        this.commitId = new MetaData("commitId", commitId)
        this.commitSubject = new MetaData("commitSubject", commitSubject)
        this.commitDate = new MetaData("commitDate", commitDate)
        this.commitAuthor = new MetaData("commitAuthor", commitAuthor)
    }

    def convertToList() {
        return [commitId, commitSubject, commitDate, commitAuthor]
    }

    @NonCPS
    @Override
    String toString() {
        return new JsonBuilder(convertToList()).toString()
    }
}
