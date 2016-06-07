import android.Keys._

import android.Dependencies.{LibraryDependency, aar}

android.Plugin.androidBuild

platformTarget in Android := "android-23"

name := "PebbleHelloWorldApp"

scalaVersion := "2.11.8"

run <<= run in Android

install <<= install in Android

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

javacOptions in (Compile, compile) ++= Seq(
  "-source", "1.7",
  "-target", "1.7",
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)

scalacOptions in (Compile, compile) ++= (
  dependencyClasspath in Compile).value.files.map("-P:wartremover:cp:" + _.toURI.toURL
)

scalacOptions in (Compile, compile) ++= Seq(
  "-P:wartremover:traverser:macroid.warts.CheckUi"
)

libraryDependencies ++= Seq(
  aar("org.macroid" %% "macroid" % "2.0.0-M3"),
  "com.android.support" % "support-v4" % "20.0.0",
  "com.getpebble" % "pebblekit" % "3.0.0",
  compilerPlugin("org.brianmckenna" %% "wartremover" % "0.11")
)

proguardScala in Android := true

proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic",
  "-keep class macroid.contrib.TextTweaks",
  "-keep class com.getpebble.android.kit.PebbleKit"
)
