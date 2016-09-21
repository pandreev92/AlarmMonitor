
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class AlarmMonitor {
	
	private RandomAlarm m;
	private JButton start;
	private JButton stop;
	private JButton refresh;
	private JTable table;
	private AlarmTableModel alarmModel;
	private Timer aRefreshTimer;
	private JCheckBox autoRefresh;
	
	AlarmMonitor(){
		 JFrame.setDefaultLookAndFeelDecorated(false);
         JFrame frame = new JFrame("Alarm Monitor");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
         
         m = new RandomAlarm();
         alarmModel = new AlarmTableModel(m.getAlarms());
         
         JPanel panel = new JPanel();
         panel.setLayout(null);
         
         table = new JTable(alarmModel);
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setSize(600, 500);
      	 scrollPane.setLocation(5, 5);
      	 table.getColumnModel().getColumn(5).setPreferredWidth(90);

         panel.add(scrollPane);
         
         start = new JButton("Start monitoring");
         start.setSize(150,30);
         start.setLocation(620, 120);
         panel.add(start);
         start.addActionListener(new startM());
         
         stop = new JButton("Stop monitoring");
         stop.setSize(150,30);
         stop.setLocation(620, 160);
         panel.add(stop);
         stop.setEnabled(false);
         stop.addActionListener(new stopM());
         
         refresh = new JButton("Refresh");
         refresh.setSize(150,30);
         refresh.setLocation(620, 200);
         panel.add(refresh);
         refresh.addActionListener(new refreshData());
         
         aRefreshTimer = new Timer(10000, new refreshData());
         autoRefresh = new JCheckBox("Auto refresh");
         autoRefresh.setSize(200,30);
         autoRefresh.setLocation(620, 240);
         panel.add(autoRefresh);
         autoRefresh.addItemListener(new autoRefreshData());
         
         frame.add(panel);
         frame.setPreferredSize(new Dimension(800, 600));
         
         frame.pack();
         frame.setVisible(true);
	}
	
	class startM implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			m.startRandomAlarm();
            stop.setEnabled(true);
            start.setEnabled(false);
            autoRefresh.setEnabled(true);
		}
	}
	
	class stopM implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			m.stopRandomAlarm();
            stop.setEnabled(false);
            start.setEnabled(true);
            autoRefresh.setEnabled(false);
		}
	}
	
	class refreshData implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(RandomAlarm.isUpdated)	alarmModel.refreshData(m.getAlarms());
		}
	}
	
	class autoRefreshData implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(autoRefresh.isSelected())
				aRefreshTimer.start();
				else aRefreshTimer.stop();
		}
	}
	
	public static void main(String[] args) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
            	  JFrame.setDefaultLookAndFeelDecorated(true);
                  new AlarmMonitor();
             }
        });
	}

}
