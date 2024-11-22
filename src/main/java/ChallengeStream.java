/* (C)2024 */
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
        final int WINNER_HAND_LENGTH = 2;
        if (player1.size() < WINNER_HAND_LENGTH || player2.size() < WINNER_HAND_LENGTH) {
            return new CardWinner();
        }

        int player1Hand = this.getHighestHandNumber(player1);
        int player2Hand = this.getHighestHandNumber(player2);

        return new CardWinner(
          player1Hand > player2Hand ? "P1" : player2Hand > player1Hand ? "P2" : "TIE",
          Math.max(player1Hand, player2Hand)
        );
    }

    private int getHighestHandNumber(List<Integer> cards) {
      return cards.stream()
        .sorted(Comparator.reverseOrder())
        .limit(2)
        .reduce(0, (a, b) -> a * 10 + b);
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
        // List stores [baseMinutes, baseMinutePrice, extraMinutePrice] respectively
        Map<String, List<Double>> basePrices = Map.of(
          "International", List.of(3.0, 7.56, 3.03),
          "National", List.of(3.0, 1.20, 0.48),
          "Local", List.of(0.0, 0.0, 0.2)
        );

        List<String> callTypes = List.of("International", "National", "Local");
        List<CallSummary> summaries = costObjectList.stream()
          .filter(call -> callTypes.contains(call.getType()))
          .map(call -> {
            List<Double> config = basePrices.get(call.getType());
            double price = call.getDuration() <= config.get(0)
              ? call.getDuration() * config.get(1)
              : config.get(1) * config.get(0)
              + (call.getDuration() - config.get(0)) * config.get(2);

            return new CallSummary(call, price);
          })
          .toList();

        double totalCost = summaries.stream()
          .mapToDouble(CallSummary::getTotalCost)
          .sum();

        return new TotalSummary(summaries, summaries.size(), totalCost);
    }
}
