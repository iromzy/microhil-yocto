FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://10-sudoers-microhil"

do_install:append () {
    install -d ${D}${sysconfdir}/sudoers.d
    install -m 0440 ${WORKDIR}/10-sudoers-microhil ${D}${sysconfdir}/sudoers.d/10-sudoers-microhil
}
