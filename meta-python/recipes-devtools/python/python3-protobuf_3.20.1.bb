DESCRIPTION = "Protocol Buffers"
HOMEPAGE = "https://developers.google.com/protocol-buffers/"
SECTION = "devel/python"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://PKG-INFO;beginline=8;endline=8;md5=53dbfa56f61b90215a9f8f0d527c043d"

inherit pypi setuptools3

SRC_URI[sha256sum] = "adc31566d027f45efe3f44eeb5b1f329da43891634d61c75a5944e9be6dd42c9"

# http://errors.yoctoproject.org/Errors/Details/184715/
# Can't find required file: ../src/google/protobuf/descriptor.proto
CLEANBROKEN = "1"

UPSTREAM_CHECK_REGEX = "protobuf/(?P<pver>\d+(\.\d+)+)/"

DEPENDS += "protobuf"

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-datetime \
    ${PYTHON_PN}-json \
    ${PYTHON_PN}-logging \
    ${PYTHON_PN}-netclient \
    ${PYTHON_PN}-numbers \
    ${PYTHON_PN}-pkgutil \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-unittest \
"

# For usage in other recipies when compiling protobuf files (e.g. by grpcio-tools)
BBCLASSEXTEND = "native nativesdk"

DISTUTILS_BUILD_ARGS += "--cpp_implementation"
DISTUTILS_INSTALL_ARGS += "--cpp_implementation"

do_compile:prepend:class-native () {
    export KOKORO_BUILD_NUMBER="1"
}
