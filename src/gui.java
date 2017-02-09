import java.awt.*;
import javax.swing.*;
public class gui extends JFrame
{
    public static JTextArea area;
    public static JTextArea area2;
    public static JScrollPane scroll;
    public gui(String label)
    {
        //Set the layout of this Frame
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        //Create a Panel that has its own layout and can have components added to it
        JPanel sidepanel = new JPanel();

        //Set the layout of the sidepanel JPanel
        sidepanel.setLayout(new BoxLayout(sidepanel, BoxLayout.X_AXIS));

        //Add the sidepanel to this Frame (on the left side)
        this.add(sidepanel, BorderLayout.SOUTH);

        //Create an editable TextArea and add it to the center of this frame
        area = new JTextArea();
        area.setEditable(false);
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.append("Hello! \n");
        scroll = new JScrollPane(area);
        scroll.getViewport().setViewPosition(new Point(0,area.getDocument().getLength()));
        JTextArea area2 = new JTextArea();
        area2.setBackground(Color.BLACK);
        area2.setForeground(Color.WHITE);
        this.add(scroll, BorderLayout.CENTER);

        sidepanel.add(area2);
    }
}
