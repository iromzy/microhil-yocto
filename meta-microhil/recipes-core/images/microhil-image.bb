DESCRIPTION = "Microhil image for raspberry"
LICENSE = "MIT"

inherit core-image
inherit extrausers

# Activate systemd resolve and network
IMAGE_INSTALL:append = " systemd"
SYSTEMD_AUTO_ENABLE_systemd-resolved = "enable"
SYSTEMD_AUTO_ENABLE_systemd-networkd = "enable"
SYSTEMD_AUTO_ENABLE_openssh = "enable"

# Personal recipe
IMAGE_INSTALL:append = " systemd-networkd-config"

# Install some usefull packages
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_INSTALL:append = " wpa-supplicant nano sudo"

# Install Avahi for mDNS
IMAGE_INSTALL:append = " avahi-daemon avahi-utils"
SYSTEMD_AUTO_ENABLE_avahi-daemon = "enable"


# Python 3 libraries
IMAGE_INSTALL:append = " python3 python3-pip "


RPI_EXTRA_CONFIG = "dtparam=spi=on dtparam=pwm=on"

PASSWD = '\$6\$hXB22UUk/m0pCe0d\$RruohQIC1.Xtm.JwVfISS6JJOgU0fvbZbhVt0INh6mGg4JLM8QzPVcZn3F92IS8dAtBYufK5gz/m/1Uv7beem.'
EXTRA_USERS_PARAMS = "\
  useradd -m -G sudo -p '${PASSWD}' microhil; passwd-expire microhil; \
  usermod -p '*' -d /root root; \
"

# Install developpement (optional) tools with packagegroup
IMAGE_INSTALL:append = " packagegroup-microhil-dev"