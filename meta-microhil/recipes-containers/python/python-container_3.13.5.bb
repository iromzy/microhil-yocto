SUMMARY = "OCI Python 3.13 image taken from Docker Hub"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# TODO try image-oci instead of doing it manuall
inherit systemd

FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
#  We pull the official multi-arch image
SRC_URI = " \
    file://python-3.13.5-bullseye.oci.tar.xz;unpack=0 \
    file://oci-image-load-python313.service \
"

OCI_IMAGE_NAME = "python"
OCI_IMAGE_TAG  = "3.13.5-bullseye"

# Systemd that loads and launches the container
SYSTEMD_SERVICE:${PN} = "oci-image-load-python313.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${datadir}/containers/oci
    install -m 0644 ${WORKDIR}/python-3.13.5-bullseye.oci.tar.xz \
        ${D}${datadir}/containers/oci/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/oci-image-load-python313.service \
                    ${D}${systemd_system_unitdir}
}

FILES:${PN} += "${datadir}/containers/oci/${OCI_IMAGE_NAME}-${OCI_IMAGE_TAG}.oci.tar.xz \
                ${systemd_system_unitdir}/oci-image-load-python313.service"

RDEPENDS:${PN} += "docker"
