def call(Map config) {
    script {
        sh """
        set +x
        echo ${config.password} | base64 -d | docker login -u ${config.username}  --password-stdin ${config.repo} >/dev/null 2>&1
        set -x
        docker build -f ${config.dockerfile} -t ${config.tag}  ${config.context}
        docker push ${config.tag}
        """
    }
}
