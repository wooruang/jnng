cmake_minimum_required (VERSION 3.1)

enable_language(CXX)
set (CMAKE_CXX_STANDARD 11)

project(jnng)


set(SRC_INCLUDE_DIR "${PROJECT_SOURCE_DIR}/src/main/c")

set(NNG_INCLUDE_DIRS "${PROJECT_SOURCE_DIR}/nng/install/include")
set(NNG_LIBRARIES "${PROJECT_SOURCE_DIR}/nng/install/lib/libnng.a")

message(STATUS "nng include - ${NNG_INCLUDE_DIRS}")
message(STATUS "nng libraries - ${NNG_LIBRARIES}")

find_package(JNI)

include_directories(${SRC_INCLUDE_DIR} ${JNI_INCLUDE_DIRS} ${NNG_INCLUDE_DIRS})

file(GLOB jnng_SRC "src/main/c/*.h" "src/main/c/*.cpp" "src/main/c/**/*.h" "src/main/c/**/*.cpp")

message(STATUS "SRC - ${jnng_SRC}")
message(STATUS "JNI - ${JNI_INCLUDE_DIRS}")

add_library(jnng SHARED ${jnng_SRC})

target_link_libraries(jnng ${JNI_LIBRARIES} ${NNG_LIBRARIES})
