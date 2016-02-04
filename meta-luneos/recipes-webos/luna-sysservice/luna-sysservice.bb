# Copyright (c) 2012-2014 LG Electronics, Inc.

SUMMARY = "Provides image manipulation, preference, timezone and ringtone services for Open webOS components"
AUTHOR = "Keith Derrick <keith.derrick@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

VIRTUAL-RUNTIME_ntp ?= "sntp"

DEPENDS = "luna-service2 libpbnjson qtbase uriparser libxml2 sqlite3 pmloglib json-c nyx-lib"

RDEPENDS_${PN} += "${VIRTUAL-RUNTIME_ntp}"

PV = "2.0.1-38+git${SRCPV}"
SRCREV = "0f7dff94b040e788d8e852c3b1c2d7ca09969269"

inherit webos_ports_fork_repo
inherit webos_cmake
inherit webos_system_bus
inherit pkgconfig

SRC_URI = " \
    ${WEBOS_PORTS_GIT_REPO_COMPLETE} \
"
S = "${WORKDIR}/git"
