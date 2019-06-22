# Copyright (c) 2019 LG Electronics, Inc.

SUMMARY = "webOS text to speech service"
SECTION = "webos/base"
AUTHOR = "Rachana Agarwal <rachana.agarwal@lge.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "glib-2.0 luna-service2 libpbnjson pmloglib json-c pulseaudio googleapis grpc"

PV = "1.0.0-5+git${SRCPV}"
SRCREV = "a309e1edd9d742dc588c17b4bae51e65aaabf0fc"

inherit webos_public_repo
inherit webos_cmake
inherit webos_system_bus

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DGOOGLEAPIS_PATH=${STAGING_INCDIR}/google"

FILES_${PN} += "${webos_sysbus_datadir}"
