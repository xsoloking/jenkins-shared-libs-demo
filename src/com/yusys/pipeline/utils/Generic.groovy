package com.yusys.pipeline.utils

import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

class Generic implements Serializable {

  def getInsecureRegistries(encryptedJsonString) {
    def args = []
    def dockerBuildDataList = new JsonSlurper().parseText(new String(encryptedJsonString.decodeBase64()))
    for(data in dockerBuildDataList) {
      if (data.repository?.trim() && data.repository.startsWith("http:")) {
          args.add("--insecure-registry=" + data.repository.split("//")[1])
      }
    }
    return new JsonBuilder(args).toString();
  }

}
