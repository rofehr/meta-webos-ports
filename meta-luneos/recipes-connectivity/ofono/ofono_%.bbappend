FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS_${PN} += "ofono-conf"

SRC_URI += " \
  file://0001-Add-support-for-the-Ericsson-F5521gw-modem.patch;striplevel=2 \
  file://ofono \
  file://ofono.service \
"

do_install_append() {
    # Override default system service configuration
    cp -v ${WORKDIR}/ofono.service ${D}${systemd_unitdir}/system/ofono.service
}

# meta-systemd sets this to disable but we as distro want it to be enabled by default
SYSTEMD_AUTO_ENABLE_forcevariable = "enable"
