/*
 * This file is part of kronos.
 *
 * kronos is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * kronos is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with kronos.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Licensed under the GNU GPL v3 License
 * SPDX-License-Identifier: GPL-3.0-only
 */


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;


public class PathHelpers
{
    /**
     * Check if given path exists.
     * @param  string_path  path as a {@link String}
     * @return  true if string_path exists
     */
    public static Boolean exists(String string_path)
    {
        return Files.exists(Paths.get(string_path));
    }

    /**
     * Create directory if it does not exist.
     * @param  string_path  path of directory
     */
    public static void ensure(String string_path)
    {
        Path path = Paths.get(string_path);

        if ( Files.exists(path) ) {
            System.out.println(MessageFormat.format("Already exists: {0}", string_path));
        }
        else {
            System.out.println(MessageFormat.format("Creating {0}...", string_path));

            try {
                Files.createDirectories(path);
            }
            catch ( Exception e ) {
                System.err.println(MessageFormat.format("Failed to create {0}!", string_path));
                System.err.println(e.getMessage());
            }
        }
    }
}
