# About

This Gradle Plugin allows you to use XMLBeans binding generation without having
to put any generated JAR files into your classpath.

# License

This software is released under the terms of the GNU Lesser General Public
License.

See  [LGPL.md](LGPL.md) and [GPL.md](GPL.md) for details.

# Usage

## Apply the plugin

### Plugins DSL

In your `settings.gradle` file, add our repository to the `pluginManagement` block:

    pluginManagement {
        repositories {
            maven { url 'https://mvn.topobyte.de' }
        }
    }

In your `build.gradle` file, add this:

    plugins {
        id 'de.topobyte.xmlbeans-gradle-plugin' version '0.2.0'
    }

### Legacy method

In your `build.gradle` file, add this:

    buildscript {
        repositories {
            maven { url 'http://mvn.topobyte.de' }
        }
        dependencies {
            classpath 'de.topobyte:gradle-xmlbeans-plugin:0.2.0'
        }
    }

    apply plugin: 'de.topobyte.xmlbeans-gradle-plugin'

## Configure the plugin

To use a file `test.xsd` configure this in your `build.gradle` file:

    xmlBeans {
        input = []
        input += 'test.xsd'
    }
