import devops.cicd.tools.Ssh

def call() {
    dockerNode(image: 'daotoanhd/devopstools:1.0.0') {
        def ssh = new Ssh()
        def serverIP = "34.126.122.163"
        def applicationDir = "/home/vagrant/devops-project-application-cowsay-api"
        stage("Pull Code") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "git pull"), "vagrant")
        }
        stage("Install Dependencies") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "npm install"), "vagrant")
        }
        stage("Restart application") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "pm2 restart api"), "vagrant")
        }
    }
}

def executeDir(dir, command) {
    return "'cd ${dir} && ${command}'"
}
