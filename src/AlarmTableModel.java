
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class AlarmTableModel extends AbstractTableModel  {
	private List<String[]> data = new ArrayList<String[]>();
	private List<Alarm> alarms;

	public AlarmTableModel(List<Alarm> al) {
		this.alarms = al;
		getData();	  
	}	  

	public void getData() {
		List<Alarm> al = new ArrayList<Alarm>();
		al.addAll(alarms);
		Collections.sort(al, new Comparator<Alarm>(){
			public int compare(Alarm a1, Alarm a2){
				return a1.getAlarmTime().compareTo(a2.getAlarmTime());
			}
		});
		
		List<String[]> sortedAlarms = new ArrayList<String[]>();
		for(Alarm a: al)
			sortedAlarms.add(a.toArrayString());
		
		data.addAll(sortedAlarms);
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
        case 0:
            return "ID";
        case 1:
            return "Alarm Number";
        case 2:
            return "Alarm Text";
        case 3:
            return "Alarm Time";
        case 4:
            return "Cancel Time";
        case 5:
            return "Acknowledge Time";
        }
        return "";
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
			data.get(row)[column] =  aValue.toString();
			for(Alarm a: alarms) {
				System.out.println(a.getId().equals(data.get(row)[0]));
				if(a.getId().equals(data.get(row)[0])){
					a.setAcknowledgeTime(aValue.toString());
					refreshData(alarms);
					break;
				}
			}
	}
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 5) return true;
		else return false;
	}
	
	public void refreshData(List<Alarm> alarms){
		data.clear();
		getData();
		this.getRowCount();
		fireTableDataChanged();
		RandomAlarm.isUpdated = false;
	}
}

