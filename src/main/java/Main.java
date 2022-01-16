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


public class Main
{
    public static void main(String [] args)
    {
        System.out.println("Config path: " + ConfPath.kronos_path_string);
        ConfPath.ensureConfPath();
        DBConn.ensureDB();

        Thread executor_thread = new Thread(() -> {
            while ( true ) {
                Executor.tryExecAll(JobService.getAll());

                System.out.println("Sleeping until next tick ...");
                try {
                    Thread.sleep(60000);
                }
                catch ( Exception e ) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        });
        executor_thread.start();

        Thread gui_thread = new Thread(MainFrame::show);
        gui_thread.start();

    }
}
