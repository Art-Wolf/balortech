#prepare a dependencies
sudo easy_install jinja2
sudo easy_install PyYAML
sudo easy_install paramiko
#doing a checkout
git clone git://github.com/ansible/ansible.git
cd ./ansible
#setting a env
source ./hacking/env-setup
cd ~
touch .ansible_hosts
#exporting a needed envs
export ANSIBLE_HOSTS=~/.ansible_hosts
export ANSIBLE_TRANSPORT=ssh
echo echo “192.168.0.62”
echo “example.org” > ~/ansible_hosts
ansible all -m ping
