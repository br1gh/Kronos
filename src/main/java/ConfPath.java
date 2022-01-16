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


import java.text.MessageFormat;


public class ConfPath
{
    public static String kronos_path_string =
            MessageFormat.format("{0}/.config/kronos", System.getProperty("os.name").startsWith("Windows") ?
                    System.getenv("APPDATA") : System.getenv("HOME"));


    /**
     * Creates a string prefixed with kronos_path_string.
     * @param  string_path  file inside kronos_path_string
     * @return  string prefixed with kronos_path_string
     */
    public static String prefix(String string_path)
    {
        return (kronos_path_string + "/" + string_path);
    }

    /**
     * Create kronos_path_string directory if it does not exist.
     */
    public static void ensureConfPath()
    {
        PathHelpers.ensure(kronos_path_string);
    }
}
