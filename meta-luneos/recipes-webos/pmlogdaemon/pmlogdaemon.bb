# Copyright (c) 2012-2019 LG Electronics, Inc.

SUMMARY = "webOS logging daemon"
AUTHOR = "Gayathri Srinivasan <gayathri.srinivasan@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "pmloglib zlib glib-2.0 librdx libpbnjson pmloglib-private luna-service2"
# show_disk_usage.sh script uses mktemp, find, xargs, and du, all of which are
# provided by busybox.
RDEPENDS_${PN} = "busybox"

PV = "3.1.0-5+git${SRCPV}"
SRCREV = "a0e12f5a746ce88cc9da68c11b9d5595bd3c31bf"

inherit webos_public_repo
inherit webos_cmake
inherit webos_system_bus
inherit webos_pmlog_config

PACKAGECONFIG ??= ""
PACKAGECONFIG[whitelist] = "-DENABLE_WHITELIST:BOOL=TRUE, -DENABLE_WHITELIST:BOOL=FALSE"

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE} \
    ${@bb.utils.contains('PACKAGECONFIG', 'whitelist', 'file://whitelist.txt', '', d)} \
"
S = "${WORKDIR}/git"

do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'whitelist', 'true', 'false', d)} ; then
        install -m 644 ${WORKDIR}/whitelist.txt ${D}${sysconfdir}/PmLogDaemon
    fi
}
FILES_${PN} += "${datadir}/PmLogDaemon"
