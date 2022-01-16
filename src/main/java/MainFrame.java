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


import java.awt.*;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static java.util.Objects.*;


public class MainFrame
{
    /**
     * Create a list containing strings from specified range and "*ANY*".
     * @param  start  range start
     * @param  stop  range end
     * @return  list of strings from start to stop and "*ANY*"
     */
    private static String [] make_list(int start, int stop)
    {
        String [] ret = new String [stop - start + 2];
        ret[0] = "*ANY*";

        int c = 1;
        for ( Integer i : IntStream.range(start, stop + 1).toArray() ) {
            ret [c] = String.valueOf(i);
            c++;
        }
        return ret;
    }

    /**
     * Extract {@link Integer} from given {@link Object},
     * if the representation of {@link Object} as a string
     * is equal to "*ANY*", then return null.
     * Pipeline: Object -> String -> Integer
     * @param  o object
     * @return  integer
     */
    private static Integer toDBInt(Object o)
    {
        String s = o.toString();
        return s.equals("*ANY*") ? null : Integer.valueOf(s);
    }

    private static java.util.List<Job> all_jobs_list;
    private static int all_jobs_list_len;

    private static java.util.List<String[]> executed_jobs_list;

    private static void doGlobalUpdate()
    {
        all_jobs_list       = JobService.getAll();
        all_jobs_list_len   = all_jobs_list.size();
        executed_jobs_list  = JobService.getExecuted();
    }

    public static void show()
    {
        Color tabs_bg = new Color(60, 63, 65);
        Color bg = new Color(43, 43, 43);
        Color text_bg = new Color(49, 51, 53);
        Color font_color = new Color(168,182,191);


        JFrame main_frame = new JFrame("Kronos");
        main_frame.setSize(550, 500);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.getContentPane().setBackground(tabs_bg);

        JTabbedPane main_tabbed_pane = new JTabbedPane();
        main_tabbed_pane.setBackground(bg);
        main_tabbed_pane.setForeground(font_color);
        main_frame.getContentPane().add(main_tabbed_pane);


        // Initiate global vars

        doGlobalUpdate();


        // Add a Job

        JPanel add_panel = new JPanel();
        add_panel.setBackground(text_bg);
        add_panel.setLayout(new GridLayout(7, 2));
        main_tabbed_pane.add("Add", add_panel);

        add_panel.add(new MainFrameText("Command: "));
        JTextField command_panel_field = new JTextField("echo works");
        command_panel_field.setForeground(font_color);
        command_panel_field.setBackground(Color.BLACK);
        add_panel.add(command_panel_field);

        add_panel.add(new MainFrameText("Month: "));
        JComboBox month_panel_field = new MainFrameComboBox(make_list(1, 12));
        add_panel.add(month_panel_field);

        add_panel.add(new MainFrameText("Month Day: "));
        JComboBox m_day_panel_field = new MainFrameComboBox(make_list(1, 31));
        add_panel.add(m_day_panel_field);

        add_panel.add(new MainFrameText("Week Day: "));
        JComboBox w_day_panel_field = new MainFrameComboBox(make_list(1, 7));
        add_panel.add(w_day_panel_field);

        add_panel.add(new MainFrameText("Hour: "));
        JComboBox hour_panel_field = new MainFrameComboBox(make_list(0, 23));
        add_panel.add(hour_panel_field);

        add_panel.add(new MainFrameText("Minute: "));
        JComboBox minute_panel_field = new MainFrameComboBox(make_list(0, 59));
        add_panel.add(minute_panel_field);

        MainFrameButton reset_button_add_panel =
            new MainFrameButton("Reset",new Color(199,84,80));
        add_panel.add(reset_button_add_panel);
        reset_button_add_panel.addActionListener((aE) -> {
            command_panel_field.setText("echo works");
            month_panel_field.setSelectedIndex(0);
            m_day_panel_field.setSelectedIndex(0);
            w_day_panel_field.setSelectedIndex(0);
            hour_panel_field.setSelectedIndex(0);
            minute_panel_field.setSelectedIndex(0);
        });

        MainFrameButton save_button_add_panel =
            new MainFrameButton("Save",new Color(88,143,70));
        add_panel.add(save_button_add_panel);
        save_button_add_panel.addActionListener((aE) -> {
            Job j = new Job(0, command_panel_field.getText(),
                    toDBInt(requireNonNull(month_panel_field.getSelectedItem())),
                    toDBInt(requireNonNull(m_day_panel_field.getSelectedItem())),
                    toDBInt(requireNonNull(w_day_panel_field.getSelectedItem())),
                    toDBInt(requireNonNull(hour_panel_field.getSelectedItem())),
                    toDBInt(requireNonNull(minute_panel_field.getSelectedItem())));
            JobService.insert(j);
        });


        // Remove a job

        JPanel remove_job_panel = new JPanel();
        remove_job_panel.setBackground(text_bg);
        main_tabbed_pane.add("Remove", remove_job_panel);

        String [] ids_of_jobs = new String[all_jobs_list_len];

        int loaded_jobs_remove_job_panel = 0;
        for ( Job j : all_jobs_list ) {
            ids_of_jobs[loaded_jobs_remove_job_panel] = (j.id == null ? "Any" : j.id.toString());
            loaded_jobs_remove_job_panel ++;
        }

        JComboBox all_jobs = new MainFrameComboBox(ids_of_jobs);
        remove_job_panel.setLayout(new GridLayout(7, 1));
        remove_job_panel.add(all_jobs);

        MainFrameButton remove_button_remove_jobs =
                new MainFrameButton("Remove", new Color(199,84,80));
        remove_job_panel.add(remove_button_remove_jobs);

        remove_button_remove_jobs.addActionListener(
                (aE) -> JobService.delete(Integer.parseInt(requireNonNull(
                        all_jobs.getSelectedItem()).toString())));


        // All Jobs

        JPanel all_panel = new JPanel();
        all_panel.setBackground(text_bg);
        main_tabbed_pane.add("All", all_panel);

        String [] column_names_all = {"Id", "Command", "Month", "Month Day", "Week Day", "Hour", "Minute"};
        JTable table_all = new JTable(new DefaultTableModel(column_names_all, 0));
        table_all.setBackground(bg);
        table_all.setForeground(font_color);

        JScrollPane scroll_pane = new JScrollPane(table_all);
        scroll_pane.getViewport().setBackground(tabs_bg);

        all_panel.add(scroll_pane);


        // Executed jobs

        JPanel executed_panel = new JPanel();
        executed_panel.setBackground(text_bg);
        main_tabbed_pane.add("Executed", executed_panel);

        String[] column_names_executed = {"Id", "Job Id","Command", "Date", "Exit Code", "Exit Output"};
        JTable table_executed = new JTable(new DefaultTableModel(column_names_executed, 0));
        table_executed.setBackground(bg);
        table_executed.setForeground(font_color);

        JScrollPane scroll_pane_executed = new JScrollPane(table_executed);
        scroll_pane_executed.getViewport().setBackground(tabs_bg);

        executed_panel.add(scroll_pane_executed);


        // Tray icon

        if ( SystemTray.isSupported() ) {
            SystemTray system_tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(
                    MainFrame.class.getResource("images/kronos.png"));
            PopupMenu tray_popup_menu = new PopupMenu("Kronos Tray");
            TrayIcon tray_icon = new TrayIcon(image, "Kronos", tray_popup_menu);
            tray_icon.setImageAutoSize(true);

            MenuItem toggle_visibility_tray_menu_item = new MenuItem("Hide / Show");
            toggle_visibility_tray_menu_item.addActionListener(
                    (aE) -> main_frame.setVisible(!main_frame.isVisible()));
            tray_popup_menu.add(toggle_visibility_tray_menu_item);

            MenuItem close_tray_menu_item = new MenuItem("Close");
            close_tray_menu_item.addActionListener((aE) -> System.exit(0));
            tray_popup_menu.add(close_tray_menu_item);

            try {
                system_tray.add(tray_icon);
            }
            catch ( Exception e ) {
                System.err.println(e.getMessage());
            }
        }


        // Show the main frame

        main_frame.setVisible(true);


        // Updater thread

        Thread updater = new Thread(() -> {
            while ( true ) {
                // Update global private vars
                doGlobalUpdate();

                // Update all Jobs table
                DefaultTableModel table_all_model = (DefaultTableModel) table_all.getModel();
                table_all_model.setRowCount(0);
                for ( Job j : all_jobs_list ) {
                    table_all_model.addRow(new String [] {
                            (j.id     == null ? "Any" : j.id.toString()),
                            j.command,
                            (j.month  == null ? "Any" : j.month.toString()),
                            (j.m_day  == null ? "Any" : j.m_day.toString()),
                            (j.w_day  == null ? "Any" : j.w_day.toString()),
                            (j.hour   == null ? "Any" : j.hour.toString()),
                            (j.minute == null ? "Any" : j.minute.toString())
                    });
                }
                table_all.setBounds(0,0,500,500);
                table_all.setPreferredScrollableViewportSize(table_all.getPreferredSize());

                // Update executed Jobs table
                DefaultTableModel table_executed_model = (DefaultTableModel) table_executed.getModel();
                table_executed_model.setRowCount(0);
                for ( String [] e : executed_jobs_list ) {
                    table_executed_model.addRow(e);
                }
                table_executed.setBounds(0,0,500,500);
                table_executed.setPreferredScrollableViewportSize(table_executed.getPreferredSize());

                try {
                    Thread.sleep(100);
                }
                catch ( Exception e ) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        });

        updater.start();

    }
}
