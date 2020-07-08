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

import org.apache.xmlbeans.impl.tool.SchemaCompiler;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CompileSchemaTask extends ConventionTask
{

    private XmlBeansPluginExtension extension;

    public CompileSchemaTask()
    {
        setGroup("build");
    }

    public void setConfiguration(XmlBeansPluginExtension extension)
    {
        this.extension = extension;
    }

    @TaskAction
    protected void generateSchema() throws IOException
    {
        Project project = getProject();

        if (extension.getInput() == null) {
            throw new InvalidUserDataException("You need to specify the input files via 'input'");
        }

        System.out.println("Generating files from input directories:");

        Path projectDir = project.getProjectDir().toPath();
        Path buildDir = project.getBuildDir().toPath();

        Path source = Util.getSourceDir(buildDir);
        Path output  = Util.getClassesDir(buildDir);

        List<Path> xsdPaths = new ArrayList<>();
        List<String> fileNames = extension.getInput();
        for (String fileName : fileNames) {
            System.out.println("xsd file: " + fileName);
            xsdPaths.add(projectDir.resolve(fileName));
        }

        File[] xsdFiles = new File[xsdPaths.size()];
        for (int i = 0; i < xsdPaths.size(); i++) {
            xsdFiles[i] = xsdPaths.get(i).toFile();
        }

        SchemaCompiler.Parameters params = new SchemaCompiler.Parameters();
        params.setBaseDir(null);
        params.setXsdFiles(xsdFiles);
        params.setOutputJar(null);
        params.setJavaSource("1.8");
        params.setSrcDir(source.toFile());
        params.setClassesDir(output.toFile());
        params.setNojavac(true);

        SchemaCompiler.compile(params);
    }

}
