package com.yusys.pipeline.utils

class DockerBuildData {

    def context;
    def dockerfile;
    def repository;
    def username;
    def password;
    def tags=[];

    DockerBuildData(context, dockerfile, repository, username, password, tags){
        this.context = context
        this.dockerfile = dockerfile
        this.repository = repository
        this.username = username
        this.password = password
        this.tags = tags
    }

}