// Copyright 2020 Sebastian Kuerten
//
// This file is part of gradle-xmlbeans-plugin.
//
// gradle-xmlbeans-plugin is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// gradle-xmlbeans-plugin is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with gradle-xmlbeans-plugin. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;

import java.nio.file.Path;

public class XmlBeansPlugin implements Plugin<Project>
{

    @Override
    public void apply(final Project project)
    {
        Logger logger = project.getLogger();
        logger.info("applying xmlbeans plugin");

        XmlBeansPluginExtension extension = project.getExtensions().create(
                "xmlBeans",
                XmlBeansPluginExtension.class);

        CompileSchemaTask task = project.getTasks().create(
                "compileSchema",
                CompileSchemaTask.class);
        task.setConfiguration(extension);

        project.getTasks().findByName("compileJava").dependsOn(task);

        if (project.getPlugins().hasPlugin(EclipsePlugin.class)) {
            project.getTasks().findByName("eclipse").dependsOn(task);
            project.getTasks().findByName("eclipseClasspath").dependsOn(task);
            project.getTasks().findByName("eclipseProject").dependsOn(task);
        }

        Path pathBuildDir = project.getBuildDir().toPath();
        Path source = Util.getSourceDir(pathBuildDir);

        SourceSet sourceSets = project.getConvention()
                .getPlugin(JavaPluginConvention.class).getSourceSets()
                .findByName("main");
        sourceSets.java(sourceSet -> {
            sourceSet.srcDir(source);
        });
    }

}
