echo "Clear the logs..."
cat /dev/null > /var/lib/tomcat8/logs/catalina.out
rm -rf /var/lib/tomcat8/logs/localhost*
rm -rf /var/lib/tomcat8/logs/cataliana.out.*
rm -rf /var/lib/tomcat8/logs/manager*
rm -rf /var/lib/tomcat8/logs/catalina.2*
rm -rf /var/lib/tomcat8/logs/host*
