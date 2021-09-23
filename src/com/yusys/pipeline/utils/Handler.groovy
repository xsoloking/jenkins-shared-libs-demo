package com.yusys.pipeline.utils

import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

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
    msgPublisher(data.toString(), Constants.RABBITMQ_EXCHANGE, Constants.RABBITMQ_START_ROUTING_KEY_DEV)
  }

  def sendEndMsg(extraData = []) {
    def data = new EndData(steps.params.flowId,
                           steps.params.flowInstanceId,
                           steps.params.taskInstanceId,
                           steps.env.JOB_NAME,
                           steps.env.BUILD_ID,
                           steps.currentBuild.currentResult)
    data.addExtraData(extraData)
    msgPublisher(data.toString(), Constants.RABBITMQ_EXCHANGE, Constants.RABBITMQ_END_ROUTING_KEY_DEV)

  }

  def msgPublisher(data, exchange, routingKey) {
    steps.rabbitMQPublisher conversion: false, data: data, exchange: exchange, rabbitName: 'rabbitmq', routingKey: routingKey
  }

  def getInsecureRegistry(encryptedJsonString) {
    def args = []
    def dockerBuildDataList = new JsonSlurper().parseText(new String(encryptedJsonString.decodeBase64()))
    for(data： dockerBuildDataList) {
      if (data.repository?.trim() && data.repository.startsWith("http:")) {
          args.add("--insecure-registry=" + data.repository.split("//")[1])
      }
    }
    return new JsonBuilder(args).toString();
  }

  def dockerBuild(encryptedJsonString) {
    def dockerBuildDataList = new JsonSlurper().parseText(new String(encryptedJsonString.decodeBase64()))
    for(data： dockerBuildDataList) {
      def tags = data.tags.join(" -t ")
      steps.sh """
          set +x
          echo ${data.password} | base64 -d | docker login -u ${data.username}  --password-stdin ${data.repo} >/dev/null 2>&1
          set -x
          docker build -f ${data.dockerfile} -t ${tags} ${data.context}
      """
      if (data.repository?.trim() && data.repository != "null") {
        steps.sh "docker push ${tags}"
      }
    }
  }
}