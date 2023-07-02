package devops.cicd.tools



def prepare(credential = "ssh-key") {
    withCredentials([file(credentialsId: credential, variable: 'FILE')]) {
        def text = readFile(FILE)
        writeFile(file: 'ssh-key', text: text)
        sh "chmod 400 ssh-key"
    }
}

def executeCommand(hostname, command, username = "cicd") {
    prepare()
    return sh(
        script: "ssh -i ssh-key -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}",
        returnStdout: true
    )
}
