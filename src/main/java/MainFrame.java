import java.awt.*;
import java.util.stream.IntStream;
import javax.swing.*;


public class MainFrame
{
    private static String [] make_list(int start, int stop)
    {
        return IntStream.range(start, stop + 1)
                .mapToObj(String::valueOf).toArray(String[]::new);
    }

    public static void show()
    {
        Color tabs_bg = new Color(60, 63, 65);
        Color bg = new Color(43, 43, 43);
        Color text_bg = new Color(49, 51, 53);
        Color font_color = new Color(168,182,191);


        JFrame main_frame = new JFrame("Kronos");
        main_frame.setSize(500, 500);
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
        reset_button_add_panel.addActionListener(new ButtonPress());

        MainFrameButton save_button_add_panel =
            new MainFrameButton("Save",new Color(88,143,70));
        add_panel.add(save_button_add_panel);
        save_button_add_panel.addActionListener(new ButtonPress());


        // All Jobs

        JPanel all_panel = new JPanel();
        all_panel.setBackground(text_bg);
        main_tabbed_pane.add("All", all_panel);


        // Executed jobs

        JPanel executed_panel = new JPanel();
        executed_panel.setBackground(text_bg);
        main_tabbed_pane.add("Executed", executed_panel);


        main_frame.setVisible(true);
    }
}
