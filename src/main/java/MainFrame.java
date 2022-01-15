import java.awt.*;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.*;


public class MainFrame
{
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

    // Object -> String -> Integer
    private static Integer toDBInt(Object o)
    {
        String s = o.toString();
        return s.equals("*ANY*") ? null : Integer.valueOf(s);
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
                    toDBInt(Objects.requireNonNull(month_panel_field.getSelectedItem())),
                    toDBInt(Objects.requireNonNull(m_day_panel_field.getSelectedItem())),
                    toDBInt(Objects.requireNonNull(w_day_panel_field.getSelectedItem())),
                    toDBInt(Objects.requireNonNull(hour_panel_field.getSelectedItem())),
                    toDBInt(Objects.requireNonNull(minute_panel_field.getSelectedItem())));
            JobService.insert(j);
        });


        // All Jobs

        JPanel all_panel = new JPanel();
        all_panel.setBackground(text_bg);
        main_tabbed_pane.add("All", all_panel);

        String[][] data_all = new String[JobService.getAll().size()][7];

        int i = 0;
        for (Job j: JobService.getAll())
        {
            data_all[i] = new String[]
                    {
                    (j.id == null ? "Any":j.id.toString()),
                    j.command,
                    (j.month == null ? "Any":j.month.toString()),
                    (j.m_day == null ? "Any":j.m_day.toString()),
                    (j.w_day == null ? "Any":j.w_day.toString()),
                    (j.hour == null ? "Any":j.hour.toString()),
                    (j.minute == null ? "Any":j.minute.toString())
                    };
            i++;
        }

        String[] column_names_all = {"Id", "Command", "Month", "Month Day", "Week Day", "Hour", "Minute"};

        JTable table_all = new JTable(data_all, column_names_all);
        table_all.setBounds(0,0,500,500);
        table_all.setPreferredScrollableViewportSize(table_all.getPreferredSize());
        table_all.setBackground(bg);
        table_all.setForeground(font_color);

        JScrollPane scroll_pane = new JScrollPane(table_all);
        scroll_pane.getViewport().setBackground(tabs_bg);

        all_panel.add(scroll_pane);

        // Executed jobs

        JPanel executed_panel = new JPanel();
        executed_panel.setBackground(text_bg);
        main_tabbed_pane.add("Executed", executed_panel);

        String[][] data_executed = new String[JobService.getExecuted().size()][6];

        String[] column_names_executed = {"Id", "Job Id","Command", "Date", "Exit Code", "Exit Output"};

        JTable table_executed =
                new JTable(JobService.getExecuted().toArray(data_executed), column_names_executed);

        table_executed.setBounds(0,0,500,500);
        table_executed.setPreferredScrollableViewportSize(table_executed.getPreferredSize());
        table_executed.setBackground(bg);
        table_executed.setForeground(font_color);

        JScrollPane scroll_pane_executed = new JScrollPane(table_executed);
        scroll_pane_executed.getViewport().setBackground(tabs_bg);

        executed_panel.add(scroll_pane_executed);

        main_frame.setVisible(true);


        if ( SystemTray.isSupported() ) {
            SystemTray system_tray = SystemTray.getSystemTray();
            // TODO: Figure out how to specify getImage path
            Image image = Toolkit.getDefaultToolkit().getImage("");
            PopupMenu tray_popup_menu = new PopupMenu("Kronos Tray");
            TrayIcon tray_icon = new TrayIcon(image, "Kronos", tray_popup_menu);
            tray_icon.setImageAutoSize(true);

            MenuItem hide_tray_menu_item = new MenuItem("Hide");
            hide_tray_menu_item.addActionListener(
                    (aE) -> main_frame.setVisible(false));
            tray_popup_menu.add(hide_tray_menu_item);

            MenuItem show_tray_menu_item = new MenuItem("Show");
            show_tray_menu_item.addActionListener(
                    (aE) -> main_frame.setVisible(true));
            tray_popup_menu.add(show_tray_menu_item);

            MenuItem close_tray_menu_item = new MenuItem("Close");
            close_tray_menu_item.addActionListener(
                    (aE) -> System.exit(0));
            tray_popup_menu.add(close_tray_menu_item);

            try {
                system_tray.add(tray_icon);
            }
            catch ( Exception e ) {
                System.err.println(e.getMessage());
            }
        }

    }
}
