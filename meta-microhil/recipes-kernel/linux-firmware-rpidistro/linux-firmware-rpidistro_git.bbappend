# Overwrites broken links for Raspberry Pi 5 BCM43455
do_install:append() {
    fwdir=${D}${nonarch_base_libdir}/firmware
    ln -s cyfmac43455-sdio-standard.bin \
        ${fwdir}/cypress/cyfmac43455-sdio.bin
}
