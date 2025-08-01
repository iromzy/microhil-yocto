DESCRIPTION = "Microhil image for raspberry"
LICENSE = "MIT"

inherit core-image
inherit extrausers

DISTRO_FEATURES:append = " wifi bluetooth"

# Activate systemd resolve and network
IMAGE_INSTALL:append = " systemd"
SYSTEMD_AUTO_ENABLE:systemd-resolved = "enable"
SYSTEMD_AUTO_ENABLE:systemd-networkd = "enable"
SYSTEMD_AUTO_ENABLE:openssh = "enable"

# Personal recipe
IMAGE_INSTALL:append = " systemd-networkd-config"

# Install some usefull packages
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_INSTALL:append = " nano sudo wpa-supplicant-cli"

# Install Avahi for mDNS
IMAGE_INSTALL:append = " avahi-daemon avahi-utils"
SYSTEMD_AUTO_ENABLE:avahi-daemon = "enable"


# Python 3 libraries
IMAGE_INSTALL:append = " python3 python3-pip "


RPI_EXTRA_CONFIG = "dtparam=spi=on dtparam=pwm=on"

# Add Docker and Docker-Compose in the image
IMAGE_INSTALL:append = " \
    docker \
    docker-compose \
"
SYSTEMD_AUTO_ENABLE:pn-docker = "enable"

# Install dokcer image
IMAGE_INSTALL:append  = " python-container"

PASSWD = '\$6\$hXB22UUk/m0pCe0d\$RruohQIC1.Xtm.JwVfISS6JJOgU0fvbZbhVt0INh6mGg4JLM8QzPVcZn3F92IS8dAtBYufK5gz/m/1Uv7beem.'
EXTRA_USERS_PARAMS = "\
  groupadd -f spi; \
  useradd -m -G sudo,spi,docker -p '${PASSWD}' microhil; \
  usermod -p '*' -d /root root; \
"

# Install developpement (optional) tools with packagegroup
IMAGE_INSTALL:append = " packagegroup-base packagegroup-microhil-dev"