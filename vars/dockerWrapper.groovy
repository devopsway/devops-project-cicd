import devops.cicd.tools.Ssh

def call() {
    dockerNode(image: 'daotoanhd/devopstools:1.0.0') {
        stage("test") {
            def ssh = new Ssh()
            ssh.prepare()

        }
    }
}
