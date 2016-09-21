
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomAlarm {

	private CopyOnWriteArrayList<Alarm> alarms;
	private Timer randomTimer;
	static boolean isUpdated = false;
	RandomAlarm(){
		alarms = new CopyOnWriteArrayList<Alarm>();
		
	}
	
	public void startRandomAlarm(){
		randomTimer = new Timer();
		TimerTask createA = new CreateAlarm(alarms);
		TimerTask closeA = new CloseAlarms(alarms);
		TimerTask deleteA = new DeleteAlarm(alarms);
		for (int i = 0; i < 10; i++)	alarms.add(new Alarm());
		
		randomTimer.scheduleAtFixedRate(createA, 1000, 30*1000);
		randomTimer.scheduleAtFixedRate(closeA, 60*1000, 30*1000);
		randomTimer.scheduleAtFixedRate(deleteA, 540*1000, 60*1000);
	}
	
	public List<Alarm> getAlarms(){
		return alarms;
	}
	
	public void stopRandomAlarm(){
		alarms.clear();
		randomTimer.cancel();
	}
	
	static int getIntRandom(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
}

class CreateAlarm extends TimerTask {
	private CopyOnWriteArrayList<Alarm> arl;
	CreateAlarm(CopyOnWriteArrayList<Alarm> al){
		arl = al;
	}
	@Override
	public void run() {
		System.out.println("Create alarm start " + new Date());
		create();
		System.out.println("Create alarm stop " + new Date());
	}
	private void create(){
		try {
			if (arl.size() < 40) {
				arl.add(new Alarm());
				System.out.println("Create new Alarm");
				RandomAlarm.isUpdated = true;
				Thread.sleep(RandomAlarm.getIntRandom(1, 15) * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

class CloseAlarms extends TimerTask {
	private CopyOnWriteArrayList<Alarm> arl;
	CloseAlarms(CopyOnWriteArrayList<Alarm> al){
		arl = al;
	}
	@Override
	public void run() {
		System.out.println("Close alarm start " + new Date());
		close();
		System.out.println("Close alarm stop " + new Date());
	}
	private void close(){
		try {
			Date currentTime = new Date();
			Collections.shuffle(arl);
			boolean isCanceled = false;
			for (Alarm a:arl) {
				long deltaSec = (int)((currentTime.getTime() - a.getAlarmTime().getTime())/1000);
				if ( (deltaSec > 600) && (a.getCancelTime() == null) ){
					a.setCancelTime(currentTime);
					System.out.println("Cancel task because > 5 min " + a.getId());
					RandomAlarm.isUpdated = true;
					continue;
				}
				else if ( (deltaSec > 60) && (a.getCancelTime() == null) && !isCanceled ) {
					a.setCancelTime(currentTime);
					System.out.println("Cancel task " + a.getId());
					RandomAlarm.isUpdated = true;
					isCanceled = true;				
				}
			}
			Thread.sleep(RandomAlarm.getIntRandom(1, 15) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class DeleteAlarm extends TimerTask {
	private CopyOnWriteArrayList<Alarm> arl;
	DeleteAlarm(CopyOnWriteArrayList<Alarm> al){
		arl = al;
	}
	@Override
	public void run() {
		System.out.println("Delete alarm start " + new Date());
		delete();
		System.out.println("Delete alarm stop " + new Date());
	}
	private void delete(){
		try {
			
			Date currentTime = new Date();
			for (Alarm a: arl){
				long deltaSec = (int) ((currentTime.getTime() - a.getAlarmTime().getTime()) / 1000); 
				if ( (deltaSec > 600) && (a.getCancelTime() != null) ) {
					System.out.println("Delete task # " + a.getId());
					arl.remove(a);
					RandomAlarm.isUpdated = true;
				}
			}
			Thread.sleep(RandomAlarm.getIntRandom(1, 5) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}