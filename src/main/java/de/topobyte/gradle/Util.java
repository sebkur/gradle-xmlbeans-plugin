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

import java.nio.file.Path;

public class Util
{

    public static Path getSourceDir(Path pathBuildDir)
    {
        return pathBuildDir.resolve("generatedSourceXmlBeans");
    }

    public static Path getClassesDir(Path pathBuildDir)
    {
        return pathBuildDir.resolve("classes/java/main");
    }

}
