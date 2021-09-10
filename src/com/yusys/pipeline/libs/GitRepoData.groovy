package com.yusys.libs

import groovy.json.JsonBuilder

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
