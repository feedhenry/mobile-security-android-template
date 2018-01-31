#!groovy

def androidSdkSlave = containerTemplate(
  name: 'jnlp',
  image: 'docker.io/aerogear/digger-android-slave-image:latest',
  args: '${computer.jnlpmac} ${computer.name}',
  ttyEnabled: false)

podTemplate(
    label: 'android-sdk',
    cloud: "openshift",
    containers: [androidSdkSlave],
    volumes: [persistentVolumeClaim(mountPath: '/opt/android-sdk-linux', claimName: 'android-sdk', readOnly: true)]) {
  node ("android-sdk") {
    stage("Checkout") {
      checkout scm
    }

    stage ("Lint") {
      sh "./gradlew lint"
    }

    stage ("Build") {
      sh "./gradlew testDebug"
    }
  }
}
