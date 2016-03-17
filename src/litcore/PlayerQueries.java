package litcore;

public class PlayerQueries {
	public enum AskCardOutcomeTypes {
	    GaveCard(0),
	    CardNotPresent(1);
		private AskCardOutcomeTypes(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		private final int value;
	}
	class AskCardQuery{
		private Player asker ,respondent;
		private Card card;
		public AskCardQuery(Player aasker,Player arespondent,Card aCard){
			asker = aasker;
			respondent = arespondent;
			card = aCard;
		}
		public Player getasker(){
			return asker;
		}
		public Player getrespondent(){
			return respondent;
		}
		Card getCard(){
			return card;
		}
	}
	class AskCardOutcome{
		private AskCardOutcomeTypes outcomeType;
		private AskCardQuery fAskCardQuery;	
		
		public AskCardOutcome(AskCardOutcomeTypes aAskCardOutcomeTypes , AskCardQuery aAskCardOutcome){
			outcomeType = aAskCardOutcomeTypes;
			fAskCardQuery = aAskCardOutcome;
		}
		public AskCardQuery getAskCardQuery(){
			return fAskCardQuery;
		}
		public AskCardOutcomeTypes getOutcomeType(){
			return outcomeType;
		}
	}
}	
