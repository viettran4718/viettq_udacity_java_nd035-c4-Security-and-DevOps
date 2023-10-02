FROM jenkins/jenkins:latest

USER root

# Docker
RUN apt-get update -qq \
    && apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
RUN apt-get update  -qq \
#    && apt-get install docker-ce=17.12.1~ce-0~debian -y
    && apt-get install docker-ce -y
RUN usermod -aG docker jenkins

# Maven

# Setting Maven Version that needs to be installed
ARG MAVEN_VERSION=3.9.4

RUN curl -fsSL http://mirrors.advancedhosters.com/apache/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_VERSION=${MAVEN_VERSION}
ENV M2_HOME /usr/share/maven
ENV maven.home $M2_HOME
ENV M2 $M2_HOME/bin
ENV PATH $M2:$PATH

USER jenkins

# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Install the needed plugins
COPY infrastructure/jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugins < /usr/share/jenkins/ref/plugins.txt

# Setup Jenkins using Jenkins server using Jenkins Configuration as Code
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml
COPY infrastructure/jenkins/casc.yaml /var/jenkins_home/casc.yaml