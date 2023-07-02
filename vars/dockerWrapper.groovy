import devops.cicd.tools.Ssh

def call() {
    dockerNode(image: 'daotoanhd/devopstools:1.0.0') {
        stage("test") {
            def ssh = new Ssh()
            ssh.prepare()
            def ret = ssh.executeCommand("34.126.122.163", "pwd", "vagrant")
            println(ret)
        }
    }
}
