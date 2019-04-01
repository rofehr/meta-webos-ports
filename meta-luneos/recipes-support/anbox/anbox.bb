SUMMARY = "Android in a Box"
DESCRIPTION = "Runtime for Android applications which runs a full Android system \
    in a container using Linux namespaces (user, ipc, net, mount) to \
    separate the Android system fully from the host."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING.GPL;md5=f27defe1e96c2e1ecd4e0c9be8967949"

SECTION = "webos/support"

SRCREV = "d521e282965462e82465045ab95d4ae1c4619685"
PV = "3.0+git${SRCPV}"

DEPENDS += "dbus-cpp libsdl2 libsdl2-image lxc glm protobuf protobuf-native gtest virtual/egl"

RDEPENDS_${PN} += "anbox-data"

# these modules are directly included in android-flavored kernels
# Note: Anbox requires kernel >= 3.18 !
RRECOMMENDS_${PN} += " \
    ashmem \
    binder \
    kernel-module-ashmem-linux \
    kernel-module-binder-linux \
    kernel-module-squashfs \
"

SRC_URI = "git://github.com/anbox/anbox \
    file://0001-Fix-dependencies-for-LuneOS.patch \
    file://0002-Fix-native-binaries-build.patch \
    file://0003-Fix-emugl-build.patch \
    file://0004-Fix-build-with-Wayland-SDL2.patch \
    file://anbox-container-manager.service \
    file://anbox-session-manager.service \
    file://anbox-bridge.network \
    file://anbox-bridge.netdev \
"
S = "${WORKDIR}/git"

# anbox-data is available only for following 4 archs
COMPATIBLE_MACHINE ?= "(^$)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"
COMPATIBLE_MACHINE_armv7ve = "(.*)"
COMPATIBLE_MACHINE_aarch64 = "(.*)"

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=debug"
EXTRA_OECMAKE += "-DWAYLAND_SUPPORT=1"
EXTRA_OECMAKE += "-DHOST_CMAKE_C_COMPILER='${BUILD_CC}'"
EXTRA_OECMAKE += "-DHOST_CMAKE_CXX_COMPILER='${BUILD_CXX}'"
EXTRA_OECMAKE += "-DHOST_CMAKE_CXX_LINK_FLAGS='${BUILD_LDFLAGS}'"

inherit webos_cmake
inherit pkgconfig

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "anbox-container-manager.service anbox-session-manager.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/anbox-container-manager.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/anbox-session-manager.service ${D}${systemd_unitdir}/system/

    install -d ${D}${systemd_unitdir}/network
    install -Dm 644 ${WORKDIR}/anbox-bridge.network ${D}${systemd_unitdir}/network/80-anbox-bridge.network
    install -Dm 644 ${WORKDIR}/anbox-bridge.netdev ${D}${systemd_unitdir}/network/80-anbox-bridge.netdev
}

FILES_${PN} += "${systemd_unitdir}"
FILES_${PN}-dev += "${libdir}/backward/BackwardConfig.cmake"
