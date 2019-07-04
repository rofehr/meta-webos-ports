# Copyright (c) 2014-2019 LG Electronics, Inc.

SUMMARY = "webOS Audiod daemon and utilities"
AUTHOR = "Manohar Babu <manohar.babu@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 libpbnjson luna-service2 pmloglib luna-prefs boost pulseaudio"
RDEPENDS_${PN} = "\
    libasound \
    libasound-module-pcm-pulse \
    libpulsecore \
    pulseaudio \
    pulseaudio-lib-cli \
    pulseaudio-lib-protocol-cli \
    pulseaudio-misc \
    pulseaudio-module-cli-protocol-tcp \
    pulseaudio-module-cli-protocol-unix \
    pulseaudio-server \
"

PV = "1.0.0-10+git${SRCPV}"
SRCREV = "714a57b3d47ac7bbf43953591f0f8095dc194059"

inherit webos_cmake
inherit webos_system_bus
inherit gettext
inherit webos_lttng
inherit webos_public_repo

WEBOS_REPO_NAME = "audiod-pro"
SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "${@bb.utils.contains('WEBOS_LTTNG_ENABLED', '1', '-DWEBOS_LTTNG_ENABLED:BOOLEAN=True', '', d)}"

EXTRA_OECMAKE += "-DAUDIOD_PALM_LEGACY:BOOLEAN=True"
EXTRA_OECMAKE += "-DAUDIOD_TEST_API:BOOLEAN=True"

FILES_${PN} += "${datadir}/alsa/"
FILES_${PN} += "/data"
FILES_${PN} += "${webos_mediadir}/internal"
