SUMMARY = "Developer tools for Microhil"
LICENSE = "MIT"
inherit packagegroup

RDEPENDS_${PN} = " \
    iproute2 \
    net-tools \
    curl \
    "
