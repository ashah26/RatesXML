package Currency;

import Parser.CurrencyParser;

import java.util.Timer;
import java.util.TimerTask;

/*** This uses a Singleton pattern. This uses java.util.Timer to schedule a task to execute once 5 seconds have passed.***/


public class Scheduler {

    private static Scheduler scheduler = null;
    private Timer timer;
    private CurrencyParser currencyParser = null;
    private int choice;
    private int seconds;

    public static Scheduler getInstance(){
        if(scheduler == null){
            scheduler = new Scheduler();
        }
        return  scheduler;
    }

    public  void setParameters(int seconds,CurrencyPair currencyPair,int choice){
        this.choice = choice;
        this.seconds = seconds;
        currencyParser = new CurrencyParser(currencyPair);
        timer = new Timer();
    }

    public  void startScheduler(){
        timer.scheduleAtFixedRate(new checkBidRate(),0,seconds*1000);
    }

    private class checkBidRate extends TimerTask {
        @Override
        public void run() {
            try{
                // Check if biding rate = target rate
                currencyParser.compareTargetAndBidRate(choice);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
