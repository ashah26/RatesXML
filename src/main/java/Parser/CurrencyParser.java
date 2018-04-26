package Parser;

import Currency.CurrencyPair;

import org.w3c.dom.Element;

/*** This class is a child class of a XMLParser class
     It compares bid rate with given target rate ***/

public class CurrencyParser extends XMLParser{
    public CurrencyParser(CurrencyPair currencyPair) {
        super(currencyPair);
    }

    /*** This method compares bid with target ***/

    public void compareTargetAndBidRate(int choice){
        Element element = getNodeElement(getDocument());
        String symbol = element.getAttribute("Symbol");

        float targetRate = currencyPair.getTargetRate();
        float bidRate = Float.parseFloat(element.getElementsByTagName("Bid").item(0).getTextContent());

        System.out.println("Current Bid Rate:"+bidRate+"\t");

        int direction = Integer.parseInt(element.getElementsByTagName("Direction").item(0).getTextContent());

        // Notify if bid = target
        if(choice == 1 && bidRate == targetRate){
            System.out.println("Current "+symbol+ " rate(bidRate) = "+bidRate+" has reached target rate "+targetRate);
            checkDirection(direction,symbol);
        } else  if(choice == 2  && bidRate > targetRate){
            System.out.println("Current "+symbol+ " rate(bidRate) = "+bidRate+" has reached above target rate "+targetRate);
            checkDirection(direction,symbol);
        }else if(choice == 3 && bidRate < targetRate){
            System.out.println("Current "+symbol+ " rate(bidRate) = "+bidRate+" has reached below target rate "+targetRate);
            checkDirection(direction,symbol);
        }


    }

    /*** This method checks if currency rate has gone up, down or remained same as compared to last rate ***/


    private void checkDirection(int direction, String symbol) {
        String strDirection = "";
        if(direction == 0){
            strDirection = "not changed";
        }else  if(direction == -1){
            strDirection = "gone down";
        }else{
            strDirection = "gone up";
        }

        System.out.println(symbol+" has "+strDirection+" as compared to last rate");
        System.out.println("System exited");
        System.exit(0);
    }

    public float getCurrentBidRate(){
        Element element = getNodeElement(getDocument());
        return Float.parseFloat(element.getElementsByTagName("Bid").item(0).getTextContent());
    }
}
