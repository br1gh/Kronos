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


import java.time.LocalDateTime;


public class Job
{
    public Integer id;
    public String  command;
    public Integer month;
    public Integer m_day;  // month day
    public Integer w_day;  // week day
    public Integer hour;
    public Integer minute;

    Job (Integer id, String command, Integer month,
         Integer m_day, Integer w_day, Integer hour, Integer minute)
    {
        this.id      = id;
        this.command = command;
        this.month   = month;
        this.m_day   = m_day;
        this.w_day   = w_day;
        this.hour    = hour;
        this.minute  = minute;
    }

    /**
     * Check if {@link Job} object's date is satisfied,
     * that is - try to match a given {@link LocalDateTime}
     * to object's month, m_day, w_day, hour and minute.
     * @param  ldt  {@link LocalDateTime} to be used
     * @return  {@link Boolean} describing whether condition is satisfied
     */
    public Boolean doesDateSatisfy(LocalDateTime ldt)
    {
        return ( (this.month    == null || ldt.getMonthValue() == this.month)
                && (this.m_day  == null || ldt.getDayOfMonth() == this.m_day)
                && (this.w_day  == null || ldt.getDayOfWeek().getValue() == this.w_day)
                && (this.hour   == null || ldt.getHour() == this.hour)
                && (this.minute == null || ldt.getMinute() == this.minute) );
    }

    /**
     * Display a {@link Job} object to standard out.
     */
    public void display()
    {
        System.out.println("------------------------------");
        System.out.println("  Job properties");
        System.out.println("  id:         " + this.id);
        System.out.println("  command:    " + this.command);
        System.out.println("  month:      " + this.month);
        System.out.println("  month day:  " + this.m_day);
        System.out.println("  week day:   " + this.w_day);
        System.out.println("  hour:       " + this.hour);
        System.out.println("  minute:     " + this.minute);
        System.out.println("------------------------------");
    }

}
