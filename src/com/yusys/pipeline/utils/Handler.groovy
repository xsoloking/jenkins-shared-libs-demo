package com.yusys.pipeline.utils

class Handler implements Serializable {

  def steps
  Handler(steps) {this.steps = steps}

  def sendStartMsg() {
    def data = new StartData(steps.params.flowId,
                           steps.params.flowInstanceId,
                           steps.params.taskInstanceId,
                           steps.env.JOB_NAME,
                           steps.env.BUILD_ID,
                           steps.env.BUILD_URL)
    msgPublisher(data.toString(), Constants.RABBITMQ_EXCHANGE, Constants.RABBITMQ_START_ROUTING_KEY)
  }

  def sendEndMsg() {
    def commitId = steps.sh(script: 'git show -s --format=%H HEAD', returnStdout: true).trim()
    def commitDate = steps.sh(script: 'git show -s --format=%ci HEAD', returnStdout: true).trim()
    def commitSubject = steps.sh(script: 'git show -s --format=%s HEAD', returnStdout: true).trim()
    def commitAuthor = steps.sh(script: 'git show -s --format="%an <%ae>" HEAD', returnStdout: true).trim()
    def data = new EndData(steps.params.flowId,
                           steps.params.flowInstanceId,
                           steps.params.taskInstanceId,
                           steps.env.JOB_NAME,
                           steps.env.BUILD_ID,
                           steps.currentBuild.currentResult)
    data.addExtraData(new GitRepoMetaData(
                            commitId,
                            commitSubject,
                            commitDate,
                            commitAuthor).convertToList())
    msgPublisher(data.toString(), Constants.RABBITMQ_EXCHANGE, Constants.RABBITMQ_END_ROUTING_KEY)

  }

  def msgPublisher(data, exchange, routingKey) {
    steps.rabbitMQPublisher conversion: false, data: data, exchange: exchange, rabbitName: 'rabbitmq', routingKey: routingKey
  }
}