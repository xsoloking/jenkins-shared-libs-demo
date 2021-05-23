def call(Map config) {
    node {
        sh '''
        docker build -f ${config.dockerfile} -t ${config.tag}  ${config.context}
        docker push ${config.tag}
        '''
    }
}
