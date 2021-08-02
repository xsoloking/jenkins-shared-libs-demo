def info(message) {
    script {
        sh  """
        echo "INFO: ${message}"
        """
    }
}

def warning(message) {
    echo "WARNING: ${message}"
}
