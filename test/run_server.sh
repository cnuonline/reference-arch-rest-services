cd ..
#export MAVEN_OPTS="-Xms1024m -Xmx1G -XX:PermSize=1024m -noverify"
mvn clean package

# if [ "${TRAVIS_PULL_REQUEST}" = "false" ]; 
#   then 
#     curl https://raw.githubusercontent.com/timkay/aws/master/aws -o aws
#     chmod u+x aws
#     ./aws put --progress "x-amz-acl: public-read" springio-guides/gs-rest-service-0.1.0.jar target/gs-rest-service-0.1.0.jar
# fi

#rm -rf target
rm -rf build
./gradlew  build
ret=$?
if [ $ret -ne 0 ]; then
exit $ret
fi
# Run the server using bootstrap
java -jar target/reference-arch-rest-service-0.1.0.jar
#rm -rf build
exit
