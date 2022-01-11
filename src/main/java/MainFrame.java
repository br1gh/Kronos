import java.awt.*;
import java.util.Arrays;
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

        JFrame main_frame = new JFrame("Kronos");
        main_frame.setSize(500, 500);
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.getContentPane().setBackground(tabs_bg);

        JTabbedPane main_tabbed_pane = new JTabbedPane();
        main_frame.getContentPane().add(main_tabbed_pane);


        // Add a Job

        JPanel add_panel = new JPanel();
        add_panel.setBackground(text_bg);
        add_panel.setLayout(new GridLayout(6, 2));
        main_tabbed_pane.add("Add", add_panel);

        add_panel.add(new JLabel("Command: "));
        JTextField command_panel_field = new JTextField("echo works");
        add_panel.add(command_panel_field);

        add_panel.add(new JLabel("Month: "));
        JComboBox month_panel_field = new JComboBox(make_list(1, 12));
        add_panel.add(month_panel_field);

        add_panel.add(new JLabel("Month Day: "));
        JComboBox m_day_panel_field = new JComboBox(make_list(1, 31));
        add_panel.add(m_day_panel_field);

        add_panel.add(new JLabel("Week Day: "));
        JComboBox w_day_panel_field = new JComboBox(make_list(1, 7));
        add_panel.add(w_day_panel_field);

        add_panel.add(new JLabel("Hour: "));
        JComboBox hour_panel_field = new JComboBox(make_list(0, 23));
        add_panel.add(hour_panel_field);

        add_panel.add(new JLabel("Minute: "));
        JComboBox minute_panel_field = new JComboBox(make_list(0, 59));
        add_panel.add(minute_panel_field);


        // All Jobs

        JPanel all_panel = new JPanel();
        main_tabbed_pane.add("All", all_panel);


        // Executed jobs

        JPanel executed_panel = new JPanel();
        main_tabbed_pane.add("Executed", executed_panel);


        // JButton button = new JButton("Button");
        // add_panel.add(button);
        // button.addActionListener(new ButtonPress());

        main_frame.setVisible(true);
    }

}
