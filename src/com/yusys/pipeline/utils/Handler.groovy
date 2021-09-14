package com.yusys.pipeline.utils

class Handler implements Serializable {

  def steps
    Handler(steps) {this.steps = steps}

  def sendStartMsg(args) {
    commitId = steps.sh(script: 'git show -s --format=%H HEAD', returnStdout: true).trim()
    commitDate = steps.sh(script: 'git show -s --format=%ci HEAD', returnStdout: true).trim()
    commitSubject = steps.sh (script: 'git show -s --format=%s HEAD', returnStdout: true).trim()
    commitAuthor = steps.sh(script: 'git show -s --format="%an <%ae>" HEAD', returnStdout: true).trim()
    println steps.env.JOB_NAME
    println steps.params.flowInstanceId
  }

  def sendEndMsg(args) {
    steps.sh "${steps.tool 'Maven'}/bin/mvn -o ${args}"
  }

}