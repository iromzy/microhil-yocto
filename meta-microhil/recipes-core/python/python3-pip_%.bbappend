do_install:append() {
    install -d ${D}${bindir}
    ln -sf /usr/bin/pip3 ${D}${bindir}/pip
}

FILES:${PN}:append = " ${bindir}/pip"