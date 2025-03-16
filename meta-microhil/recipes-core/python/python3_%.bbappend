do_install:append() {
    install -d ${D}${bindir}
    ln -sf /usr/bin/python3 ${D}${bindir}/python
}

FILES:${PN}:append = " ${bindir}/python"