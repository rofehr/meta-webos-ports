# Copyright (c) 2017 LG Electronics, Inc.

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

# Same as in base recipe but dropped the nobash.patch
SRC_URI = "${DEBIAN_MIRROR}/main/b/base-passwd/base-passwd_${PV}.tar.gz \
           file://add_shutdown.patch \
           file://input.patch \
           file://disable-docs.patch \
           file://passwd.master \
           file://group.master \
          "

do_configure_prepend() {
    # nobash.patch has interesting side-effect which we still need
    sed -i 's%^root:[^:]*:%root::%g' ${S}/passwd.master
    cp -v ${WORKDIR}/passwd.master ${S}/
    cp -v ${WORKDIR}/group.master ${S}/
}
