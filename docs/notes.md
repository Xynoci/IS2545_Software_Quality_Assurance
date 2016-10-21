# Notes

### Travis 

#### Default builds

```bash
export DEBIAN_FRONTEND=noninteractive
git clone https://github.com/Xynoci/IS2545_Software_Quality_Assurance/
export SAUCE_USERNAME=xynoci
jdk_switcher use oraclejdk8
java -Xmx32m -version
javac -J-Xmx32m -version
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V && mvn test -B
```

#### The Build Lifecycle

Install `apt addons`
`before_install`
`install`
`before_script`
`script`
`after_success` or `after_failure`
OPTIONAL `before_deploy`
OPTIONAL `deploy`
OPTIONAL `after_deploy`
`after_script`

