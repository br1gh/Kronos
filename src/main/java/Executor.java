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


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;


public class Executor
{
    /**
     * Executes command of a given {@link Job}.
     * Also, updates Kronos database's executions table with
     * a {@link Execution} object.
     * @param  job  job from which a command should be used
     */
    public static void jobExec(Job job)
    {
        System.out.println("Executing: " + job.command);
        try {
            ProcessBuilder process_builder = new ProcessBuilder();
            if ( System.getProperty("os.name").startsWith("Windows") ) {
                process_builder.command("cmd.exe", "/c", job.command);
            }
            else {
                process_builder.command("sh", "-c", job.command);
            }
            Process p = process_builder.start();
            p.waitFor();

            BufferedReader buf =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
            String p_line = "";
            StringBuilder p_exit_output = new StringBuilder();

            while ( p_line != null ) {
                p_exit_output.append(p_line).append("\n");
                p_line = buf.readLine();
            }

            String date = LocalDateTime.now().toString();
            Execution execution =
                new Execution(0, job.id, date,
                              p.exitValue(),
                              p_exit_output.toString());
            ExecutionService.insert(execution);
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * For each of given jobs checks if current {@link LocalDateTime} satisfies
     * the time requirements, if it does jobExec is called on that job.
     * @param  jobs  list of {@link Job}s
     */
    public static void tryExecAll(List<Job> jobs)
    {
        LocalDateTime time_now = LocalDateTime.now();
        for ( Job job : jobs ) {
            if ( job.doesDateSatisfy(time_now) ) {
                jobExec(job);
            }
        }
    }

}
