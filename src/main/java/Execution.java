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


public class Execution
{
    public Integer id;
    public Integer job_id;
    public String  date;
    public Integer exit_code;
    public String  exit_output;

    Execution (Integer id, Integer job_id, String date,
         Integer exit_code, String exit_output)
    {
        this.id           = id;
        this.job_id       = job_id;
        this.date         = date;
        this.exit_code    = exit_code;
        this.exit_output  = exit_output;
    }

    /**
     * Display a {@link Execution} object to standard out.
     */
    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Execution properties");
        System.out.println("  id:           " + this.id);
        System.out.println("  job id:       " + this.job_id);
        System.out.println("  date:         " + this.date);
        System.out.println("  exit code:    " + this.exit_code);
        System.out.println("  exit output:  " + this.exit_output);
        System.out.println("------------------------------");
    }

}
