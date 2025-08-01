SUMMARY = "Developer tools for Microhil"
LICENSE = "MIT"
inherit packagegroup

RDEPENDS:${PN} = " \
    iproute2 \
    net-tools \
    curl \
    "
