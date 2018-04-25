SUMMARY = "LuneOS Telephony management daemon"
SECTION = "webos/services"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "luna-service2 json-c glib-2.0 luna-prefs glib-2.0-native"
RRECOMMENDS_${PN} += "ofono mmsd"

PV = "0.1.0-1+git${SRCPV}"
SRCREV = "4b80014da16e3ca424f34082010a1e7e034156f5"

inherit webos_ports_repo
inherit webos_filesystem_paths
inherit webos_cmake
inherit pkgconfig
inherit webos_system_bus
inherit webos_systemd

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE};branch=webosose"
S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}${webos_sysconfdir}/db/kinds
    install -m 0644 ${S}/files/db8/kinds/* ${D}${webos_sysconfdir}/db/kinds

    install -d ${D}${webos_sysconfdir}/db/permissions
    install -m 0644 ${S}/files/db8/permissions/* ${D}${webos_sysconfdir}/db/permissions

    install -d ${D}${webos_sysconfdir}/activities/com.palm.telephony
    install -v -m 644 ${S}/files/activities/com.palm.telephony/* \
        ${D}${webos_sysconfdir}/activities/com.palm.telephony
}

