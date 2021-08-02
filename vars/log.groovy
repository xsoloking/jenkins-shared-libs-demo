def info(message) {
    script {
        sh  "echo ${message}"
    }
}

def warning(message) {
    echo "WARNING: ${message}"
}
