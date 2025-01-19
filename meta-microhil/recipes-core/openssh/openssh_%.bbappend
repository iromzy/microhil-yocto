FILESEXTRAPATHS:prepend = "${THISDIR}/files:"
SRC_URI += "file://sshd_config"

do_install.append () {
    install -d ${D}${sysconfdir}/ssh
    install -m 0644 ${WORKDIR}/sshd_config ${D}${sysconfdir}/ssh/sshd_config
}