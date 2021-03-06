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


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class JobService
{
    /**
     * Insert new record into executions table.
     * @param  job  {@link Job} object to be inserted
     */
    public static void insert(Job job)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into jobs" +
                    "( command, month, m_day, w_day, hour, minute )" +
                    "values (" +
                    "'" + job.command + "' , " +
                    job.month         + "  , " +
                    job.m_day         + "  , " +
                    job.w_day         + "  , " +
                    job.hour          + "  , " +
                    job.minute        + "    " +
                    ");");
            System.out.println("------------------------------");
            System.out.println("Added new job");
            job.display();
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }
    }

    public static void delete(int id)
    {
        Connection conn = DBConn.establishConn();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM jobs WHERE id = " + id + ";");
            System.out.println("------------------------------");
            System.out.println("Deleted a job of id = " + id);
            System.out.println("------------------------------");
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }
    }

    /**
     * Get nullable integer from {@link ResultSet}.
     * @param  rs  {@link ResultSet} from which result will e extracted
     * @param  what  index if wanted {@link Integer}
     * @return  nullable {@link Integer}
     */
    private static Integer resultGetNInt(ResultSet rs, String what)
    {
        Integer result = null;
        try {
            result = rs.getObject(what) == null ? null : rs.getInt(what);
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Get all execution records from Kronos database.
     * @return  list of {@link Job} objects
     */
    public static List<Job> getAll()
    {
        List<Job> result = new LinkedList<>();
        Connection conn = DBConn.establishConn();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jobs");

            while ( rs.next() ) {
                Integer id       = rs.getInt("id");
                String  command  = rs.getString("command");
                Integer month    = resultGetNInt(rs, "month");
                Integer m_day    = resultGetNInt(rs, "m_day");
                Integer w_day    = resultGetNInt(rs, "w_day");
                Integer hour     = resultGetNInt(rs, "hour");
                Integer minute   = resultGetNInt(rs, "minute");
                result.add(new Job(id, command, month, m_day, w_day, hour, minute));
            }
        }
        catch ( Exception e ) {
             System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }

        return result;
    }

    /**
     * Joins jobs with executions.
     * @return  list of executions along with {@link Job} command
     */
    public static List<String[]> getExecuted()
    {
        List<String[]> result = new LinkedList<>();
        Connection conn = DBConn.establishConn();

        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT executions.id, job_id,command, date, exit_code, exit_output " +
                    "FROM executions INNER JOIN jobs ON executions.job_id = jobs.id";
            ResultSet rs = stmt.executeQuery(query);

            while ( rs.next() ) {
                String id           = resultGetNInt(rs, "id").toString();
                String job_id       = resultGetNInt(rs, "job_id").toString();
                String command      = rs.getString("command");
                String date         = rs.getString("date");
                String exit_code    = resultGetNInt(rs, "exit_code").toString();
                String exit_output  = rs.getString("exit_output");
                result.add(new String [] {id, job_id, command, date, exit_code, exit_output});
            }
        }
        catch ( Exception e ) {
            System.err.println(e.getMessage());
        }
        finally {
            DBConn.closeConn(conn);
        }

        return result;
    }

}
