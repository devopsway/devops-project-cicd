package devops.cicd.tools



def getSshKeyPath() {
    withCredentials([file(credentialsId: 'ssh-key', variable: 'ssh_key')]) {
        return ssh_key
    }
}

def executeCommand(hostname, command, username = "cicd") {
    def keyPath = getSshKeyPath()
    return sh(
        script: "ssh -i $keyPath -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}",
        returnStdout: true
    )
}
