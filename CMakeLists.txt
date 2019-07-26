cmake_minimum_required (VERSION 3.0)

enable_language(CXX)

project(jnng)

find_package(JNI)

include_directories(${JNI_INCLUDE_DIRS})

file(GLOB jnng_SRC "src/main/c/*.h" "src/main/c/*.cpp" "src/main/c/**/*.h" "src/main/c/**/*.cpp")

message(STATUS "SRC - ${jnng_SRC}")
message(STATUS "JNI - ${JNI_INCLUDE_DIRS}")

add_library(jnng SHARED ${jnng_SRC} )

target_link_libraries(jnng ${JNI_LIBRARIES})
