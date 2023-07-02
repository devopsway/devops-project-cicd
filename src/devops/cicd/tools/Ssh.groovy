package devops.cicd.tools



def getSshKeyPath() {
    withCredentials([file(credentialsId: 'ssh-key', variable: 'FILE')]) {
        def text = readFile(FILE)
        writeFile(file: 'ssh-key', text: text)
    }
    sh "cat ssh-key"
}

def executeCommand(hostname, command, username = "cicd") {
    return sh(
        script: "ssh -i ssh-key -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}",
        returnStdout: true
    )
}
