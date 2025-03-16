SUMMARY = "Network configuration for systemd-networkd"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI = " \
    file://eth0.network \
    file://eth0.100.netdev \
    file://eth0.100.network \
"

inherit allarch

do_install() {
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth0.network ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${WORKDIR}/eth0.100.network ${D}${sysconfdir}/systemd/network/
    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/eth0.100.netdev ${D}${sysconfdir}/systemd/network/
}

FILES:${PN} += "${sysconfdir}/systemd/network/*.network ${sysconfdir}/systemd/network/*.netdev"