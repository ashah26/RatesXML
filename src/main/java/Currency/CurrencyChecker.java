package Currency;

import Parser.CurrencyParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*** This class accepts currency name and target from user and validates them.
  It also calls methods from Scheduler class to check biding rate. ***/

public class CurrencyChecker {

    public static void main(String[] args) {
	// write your code here

        CurrencyPair currencyPair = null;
        Scheduler scheduler = null;
        CurrencyParser currencyParser= null;
        BufferedReader bufferedReader = null;
        Scanner scanner = null;

        String currencyName = "EURUSD";
        String bidRate = "";
        float targetRate = 1.16332f;
        int choice = 1;
        int schedulerInterval = 5;
        String oneLine = "";

        // Instantiate CurrencyPair
        currencyPair = new CurrencyPair();
        // Instantiate CurrencyParser
        currencyParser = new CurrencyParser(currencyPair);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);

        System.out.println("Enter name of Currency Pair: ");
        currencyName = scanner.next();
        currencyPair.setCurrencyPairName(currencyName);

        // get current bid rate
        bidRate = String.valueOf(currencyParser.getCurrentBidRate());

        System.out.println("Current "+currencyName+" rate(bid) is: "+bidRate);

        System.out.println("Enter Target Rate: ");
        targetRate = Float.parseFloat(scanner.next());
        currencyPair.setTargetRate(targetRate);

        System.out.println("Enter 1  to get notified when BidRate = TaregetRate");
        System.out.println("Enter 2  to get notified when BidRate > TaregetRate");
        System.out.println("Enter 3  to get notified when BidRate < TaregetRate");

        try{
            choice = Integer.parseInt(scanner.next());
        }catch (NumberFormatException e){
            System.out.println("Invalid Choice,so exiting the system");
            System.exit(0);
        }

        if(choice != 1 && choice != 2 && choice != 3){
            System.out.println("Invalid Choice,so exiting the system");
            System.exit(0);
        }else{
            scheduler = Scheduler.getInstance();
            scheduler.setParameters(schedulerInterval,currencyPair,choice);
            scheduler.startScheduler();
        }

        System.out.println("Current currency-pair is "+currencyName+" and Target Rate is: "+targetRate);
        System.out.println("Checking for currency-pair Bid Rate every "+schedulerInterval+" seconds.");
        System.out.println("Press Enter key anytime to exit");

        while (true){
            try {
                oneLine = bufferedReader.readLine();
                if(oneLine.equals("")){
                    System.out.println("You pressed Enter key.So, system exited");
                    bufferedReader.close();
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
