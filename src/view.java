import tool.L;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

//注册表
public class view extends JFrame {
    JTextField username;
    JPasswordField password;
    JTextField address;
    JTextField databasename;
    JButton confirm;
    JButton cancle;
    JComboBox comboBox;
    boolean ok;
    JDialog dialog;

    private static final int log_height = 300;
    private static final int log_width = 200;

    view() {
        init();
        addlis();
    }

    public void init() {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
        comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(120, 20));
        String[] val = null;
        val = RegistUtil.read();
        if (val[0].equals("Oracle")) {
            comboBox.addItem("Oracle");
            comboBox.addItem("SqlServer");
        } else {
            comboBox.addItem("SqlServer");
            comboBox.addItem("Oracle");
        }
        L.d(Arrays.toString(val));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 300));
        setSize(200, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("数据库:  "));
        mainPanel.add(comboBox);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // 登录界面布局使用FlowLayout 设置大小就自动挤压成一行一行的
        panel.add(new JLabel("账号:    "));

        username = new JTextField(val[1]);
        username.setPreferredSize(new Dimension(120, 20));
        panel.add(username);

        panel.add(new JLabel("密码:    "));

        password = new JPasswordField(val[2]);
        password.setPreferredSize(new Dimension(120, 20));
        panel.add(password);

        panel.add(new JLabel("地址:    "));

        address = new JTextField(val[3]);
        address.setPreferredSize(new Dimension(120, 20));
        panel.add(address);

        panel.add(new JLabel("数据库名:"));

        databasename = new JTextField(val[4]);
        databasename.setPreferredSize(new Dimension(120, 20));
        panel.add(databasename);
        add(mainPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        confirm = new JButton("确定");

        cancle = new JButton("取消");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirm);
        buttonPanel.add(cancle);
        add(buttonPanel, BorderLayout.SOUTH);
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_width = (int) screensize.getWidth();
        int screen_height = (int) screensize.getHeight();
        setBounds(screen_width / 2 - (log_width / 2), screen_height / 2 - (log_height / 2), log_width, log_height);

    }

    public void addlis() {
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String[] val = {(String) comboBox.getSelectedItem(), username.getText(), password.getText(), address.getText(), databasename.getText()};
                for (int i = 0; i < 5; i++) {
                    System.out.println(val[i]);
                }
                RegistUtil.write(val);
                JOptionPane.showMessageDialog(null, "写入成功！", "系统提示", JOptionPane.CLOSED_OPTION);
                System.exit(0);
            }
        });
        cancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                username.setText("");
                password.setText("");
                address.setText("");
                databasename.setText("");
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                view frame = new view();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

    }

}
