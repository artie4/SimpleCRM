FROM centos

RUN yum install -y java

VOLUME /tmp
ADD /target/web-customer-tracker.jar wct.jar
RUN sh -c 'touch /wct.jar'
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "/wct.jar"]