package com.yusys.pipeline.utils

class DockerBuildData {

    def content;
    def dockerfile;
    def repository;
    def username;
    def password;
    def tags=[];

    DockerBuildData(content, dockerfile, repository, username, password, tags){
        this.content = content
        this.dockerfile = dockerfile
        this.repository = repository
        this.username = username
        this.password = password
        this.tags = tags
    }

}