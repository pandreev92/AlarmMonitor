
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alarm {

private static int count = 0;
private String id;
private Integer alarmNumber;
private String alarmText;
private Date alarmTime;
private Date cancelTime;
private String acknowledgeTime;
static SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
static SimpleDateFormat formatID = new SimpleDateFormat("yyMMddhhmmss");

Alarm(){
	alarmNumber = count+1;
	count = count+1;
	alarmTime = new Date();
	id = (formatID.format(alarmTime)) + alarmNumber;
	setAlarmText("Alert#"+alarmNumber);
	setCancelTime(null);
	setAcknowledgeTime(null);	
}

Alarm(String alarmText){
	this();
	setAlarmText(alarmText);
}

public String getId() {
	return id;
}

public Integer getAlarmNumber() {
	return alarmNumber;
}

public String getAlarmText() {
	return alarmText;
}

public void setAlarmText(String alarmText) {
	this.alarmText = alarmText;
}

public Date getAlarmTime() {
	return alarmTime;
}

public String getCancelTime() {
	return (cancelTime == null) ? null : formatTime.format(cancelTime);
}

public void setCancelTime(Date cancelTime) {
	this.cancelTime = cancelTime;
}

public String getAcknowledgeTime() {
	return (acknowledgeTime == null) ? null : acknowledgeTime;
}

public void setAcknowledgeTime(String acknowledgeTime) {
	this.acknowledgeTime = acknowledgeTime;
}

@Override
public String toString(){
	return id + ", " + alarmNumber + ", " + alarmText + ", " + formatTime.format(alarmTime) + ", " + this.getCancelTime() + ", " + acknowledgeTime;
}

public String[] toArrayString(){
	String[] a = { id, alarmNumber.toString(), alarmText, formatTime.format(alarmTime), this.getCancelTime(), acknowledgeTime }; 
	return a;
}

}
