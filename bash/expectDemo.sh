#!/usr/bin/expect -f

if {[llength $argv] != 1} {
puts "usage: ssh.exp server"
exit 1
}

set server [lrange $argv 0 0]
set timeout 60

spawn ssh -i key.pem ec2-user@$server

expect "*connecting (yes/no)*"
send -- "yes\r"

expect "*~]$*"

send -- "sudo su -c 'rpm -Uvh http://dl.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm'\r"

send -- "nohup sudo su -c 'yum install -y R R-core R-core-devel R-devel' &\r"

expect "*~]$*"
send -- "exit\r"

interact
