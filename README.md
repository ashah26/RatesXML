# RatesXML

A Currency broker company has asked to build a system that notifies them when a currency pair rate reaches the target rate. User configures the currency pair and target rate.

There are two papckages: 1) src.main.java.Currency   2) src.main.java.Parser

Parser Package :
1) XMLParser : To parse the given link into XML.
2) CurrencyParser : To check the target rate and bid rate and notify the user

Currency Package:
1) CurrencyPair : An entity class which stores currency name and target rate obtained from user.
2) Scheduler : To schedule a check every 5 seconds of whether the target =/>/< bid rate.
3) CurrencyChecker : This class accepts currency name and target from user and validates them.It also calls methods from Scheduler class to check biding rate
