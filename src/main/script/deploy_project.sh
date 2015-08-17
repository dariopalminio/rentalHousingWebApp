#!/bin/bash

alias tomcat_start='sudo /usr/share/tomcat7/bin/startup.sh'
alias tomcat_stop='sudo /usr/share/tomcat7/bin/shutdown.sh'

export CATALINA_HOME=/usr/share/tomcat7
export WEBAPP_HOME=/usr/share/tomcat7/webapps
export WORKSPACE_ECLIPSE=$HOME/Workspace/luna_workspace

tomcat_stop

rm -rf $CATALINA_HOME/logs/*
sudo rm $WEBAPP_HOME/daroRentalHouseProjectAr.war
sudo rm -rf $WEBAPP_HOME/daroRentalHouseProjectAr
cp $WORKSPACE_ECLIPSE/daroRentalHouseProjectAr/target/daroRentalHouseProjectAr.war $WEBAPP_HOME

tomcat_start
