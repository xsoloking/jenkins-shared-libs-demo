def call(Map config) {
    node {
        sh "echo ${config.password} | base64 -d | docker login -u ${config.username}  --password-stdin ${config.repo}"
        sh """
        docker build -f ${config.dockerfile -t ${config.tag}  ${config.context}
        docker push ${config.tag}
        """
    }
}
