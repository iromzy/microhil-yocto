# systemd_%.bbappend
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://60-spi.rules"

do_install:append() {
    # ${libdir} → /lib (ou /usr/lib si configuré différemment) sur la cible
    install -d ${D}${libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-spi.rules \
        ${D}${libdir}/udev/rules.d/60-spi.rules
}

FILES:${PN} += "${libdir}/udev/rules.d/60-spi.rules"