def call(Map config) {
        script {
            checkout([$class: 'GitSCM', branches: [[name: config.branch]], extensions: [[$class: 'CloneOption', depth: 1, noTags: false, reference: '', shallow: true], [$class: 'RelativeTargetDirectory', relativeTargetDir: config.subDir]], userRemoteConfigs: [[credentialsId: config.credentialsId, url: config.url]]])
        }
}
