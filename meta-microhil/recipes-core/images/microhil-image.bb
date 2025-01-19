DESCRIPTION = "Microhil image for raspberry"
LICENSE = "MIT"

inherit core-image

# Activate systemd resolve and network
IMAGE_INSTALL:append = " systemd"
SYSTEMD_AUTO_ENABLE_systemd-resolved = "enable"
SYSTEMD_AUTO_ENABLE_systemd-networkd = "enable"
SYSTEMD_AUTO_ENABLE_openssh = "enable"

# Personal recipe
IMAGE_INSTALL:append = " systemd-networkd-config "

# Install some usefull packages
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_INSTALL:append = " wpa-supplicant nano sudo"

# Python 3 libraries
IMAGE_INSTALL:append = " python3 python3-pip "


RPI_EXTRA_CONFIG = "dtparam=spi=on dtparam=pwm=on"

EXTRA_USERS_PARAMS = "\
  useradd -m -G sudo -p '\$6\$hXB22UUk/m0pCe0d\$RruohQIC1.Xtm.JwVfISS6JJOgU0fvbZbhVt0INh6mGg4JLM8QzPVcZn3F92IS8dAtBYufK5gz/m/1Uv7beem.' microhil; \
  usermod -p '*' -d /root root; \
"