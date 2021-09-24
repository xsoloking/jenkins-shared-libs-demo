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

  def getJunitResults(url) {
    @Grab('org.jsoup:jsoup:1.14.2')
    def doc = org.jsoup.Jsoup.connect(url).get()
    def table = doc.select("table[class=bodyTable]").first();
    def tra = table.select("tr[class=a]").select("th").iterator()
    def trb = table.select("tr[class=b]").select("th").iterator()
    println("Value 1: " + tra.next().text())
    println("Value 2: " + tra.next().text())
    println("Value 3: " + tra.next().text())
    println("Value 4: " + tra.next().text())
  }

}
