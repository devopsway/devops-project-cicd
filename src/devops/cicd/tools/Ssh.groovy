package devops.cicd.tools



def prepare() {
    withCredentials([file(credentialsId: 'ssh-key', variable: 'ssh_key')]) {
        writeFile(file: "ssh-key", text: "$ssh_key")
        sh "cat ssh-key"
        sh "chmod 400 ssh-key &&  ls -al"
    }
    sh "cat ssh-key"
}

def executeCommand(hostname, command, username = "cicd") {
    return sh(
        script: "ssh -i ssh-key -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}",
        returnStdout: true
    )
}
