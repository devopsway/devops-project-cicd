package devops.cicd.tools



def prepare() {
    withCredentials([string(credentialsId: 'ssh-key', variable: 'ssh_key')]) {
        writeFile(file: "ssh-key", text: "$ssh_key")
        sh "ls -al"
    }
}

def executeCommand(hostname, command, username = "cicd") {
    return sh(
        script: "ssh -i ssh-key -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}",
        returnStdout: true
    )
}
