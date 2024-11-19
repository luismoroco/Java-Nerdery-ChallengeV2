/* (C)2024 */
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import mocks.CallCostObject;
import mocks.CallSummary;
import mocks.CardWinner;
import mocks.TotalSummary;

public class ChallengeStream {

    /**
     * One stack containing five numbered cards from 0-9 are given to both players. Calculate which hand has winning number.
     * The winning number is calculated by which hard produces the highest two-digit number.
     *
     * calculateWinningHand([2, 5, 2, 6, 9], [3, 7, 3, 1, 2]) ➞ true
     *  P1 can make the number 96
     *  P2 can make the number 73
     *  P1 win the round since 96 > 73
     *
     * The function must return which player hand is the winner and the two-digit number produced. The solution must contain streams.
     *
     * @param player1  hand, player2 hand
     */
    public CardWinner calculateWinningHand(List<Integer> player1, List<Integer> player2) {
        // YOUR CODE HERE...
        final int HAND_LENGTH = 2;
        if (player1.size() < HAND_LENGTH || player2.size() < HAND_LENGTH) {
            return new CardWinner();
        }

        int firstPlayerHighestNumber = player1.stream()
          .sorted(Comparator.reverseOrder())
          .limit(HAND_LENGTH)
          .map(String::valueOf)
          .collect(Collectors.collectingAndThen(
            Collectors.joining(),
            Integer::parseInt
          ));

        int secondPlayerHighestNumber = player2.stream()
          .sorted(Comparator.reverseOrder())
          .limit(HAND_LENGTH)
          .map(String::valueOf)
          .collect(Collectors.collectingAndThen(
            Collectors.joining(),
            Integer::parseInt
          ));

        return
          switch (Integer.compare(firstPlayerHighestNumber, secondPlayerHighestNumber)) {
              case 0 -> new CardWinner("TIE", firstPlayerHighestNumber);
              case 1 -> new CardWinner("P1", firstPlayerHighestNumber);
              default -> new CardWinner("P2", secondPlayerHighestNumber);
          };
    }

    /**
     * Design a solution to calculate what to pay for a set of phone calls. The function must receive an
     * array of objects that will contain the identifier, type and duration attributes. For the type attribute,
     * the only valid values are: National, International and Local
     *
     * The criteria for calculating the cost of each call is as follows:
     *
     * International: first 3 minutes $ 7.56 -> $ 3.03 for each additional minute
     * National: first 3 minutes $ 1.20 -> $ 0.48 per additional minute
     * Local: $ 0.2 per minute.
     *
     * The function must return the total calls, the details of each call (the detail received + the cost of the call)
     * and the total to pay taking into account all calls. The solution must be done only using streams.
     *
     * @param {Call[]} calls - Call's information to be processed
     *
     * @returns {CallsResponse}  - Processed information
     */
    public TotalSummary calculateCost(List<CallCostObject> costObjectList) {
        // YOUR CODE HERE...
        abstract class Call {
          protected int BASE_MINUTES = 0;
          protected double EXTRA_MINUTE_COST = 0.0;
          protected double BASE_MINUTE_COST = 0.0;

          public abstract double getCost(CallCostObject call);
          public double getExtraCost(int duration) {
            if (EXTRA_MINUTE_COST == 0.0) {
              return 0.0;
            }

            return duration > BASE_MINUTES
              ? (duration - BASE_MINUTES) * EXTRA_MINUTE_COST : 0.0;
          }
        }

        class InternationalCall extends Call {
           {
            BASE_MINUTES = 3;
            EXTRA_MINUTE_COST = 3.03;
            BASE_MINUTE_COST = 7.56;
          }

          @Override
          public double getCost(CallCostObject call) {
            if (call.getDuration() <= BASE_MINUTES) {
              return BASE_MINUTE_COST * call.getDuration();
            }

            return (BASE_MINUTE_COST * BASE_MINUTES) + this.getExtraCost(call.getDuration());
          }
        }

        class NationalCall extends Call {
          {
            BASE_MINUTES = 3;
            EXTRA_MINUTE_COST = 0.48;
            BASE_MINUTE_COST = 1.20;
          }

          @Override
          public double getCost(CallCostObject call) {
            if (call.getDuration() < BASE_MINUTES) {
              return BASE_MINUTE_COST * call.getDuration();
            }

            return BASE_MINUTE_COST * BASE_MINUTES + this.getExtraCost(call.getDuration());
          }
        }

        class LocalCall extends Call {
          {
            BASE_MINUTE_COST = 0.2;
          }

          @Override
          public double getCost(CallCostObject call) {
            return BASE_MINUTE_COST * call.getDuration();
          }
        }

        Map<String, Call> strategies = Map.of(
          "International", new InternationalCall(),
          "National", new NationalCall(),
          "Local", new LocalCall()
        );

        List<String> callTypes = List.of("International", "National", "Local");
        List<CallSummary> summaries = costObjectList.stream()
          .filter(call -> callTypes.contains(call.getType()))
          .map(call -> {
            Call strategy = strategies.get(call.getType());
            return new CallSummary(call, strategy.getCost(call));
          })
          .toList();

        double totalCost = summaries.stream()
          .mapToDouble(CallSummary::getTotalCost)
          .sum();

        return new TotalSummary(summaries, summaries.size(), totalCost);
    }
}
