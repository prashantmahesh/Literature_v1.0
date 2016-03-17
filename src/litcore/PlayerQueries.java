package litcore;

public class PlayerQueries {
	public enum eAskCardOutcomeTypes {
	    GaveCard(0),
	    CardNotPresent(1);
		private eAskCardOutcomeTypes(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		private final int value;
	}
	class AskCardQuery{
		private Player Asker ,Respondent;
		private Card Fcard;
		public AskCardQuery(Player aAsker,Player aRespondent,Card aCard){
			Asker = aAsker;
			Respondent = aRespondent;
			Fcard = aCard;
		}
		public Player getAsker(){
			return Asker;
		}
		public Player getRespondent(){
			return Respondent;
		}
		Card getCard(){
			return Fcard;
		}
	}
	class AskCardOutcome{
		private eAskCardOutcomeTypes fAskCardOutcomeTypes;
		private AskCardQuery fAskCardQuery;
		
		public AskCardOutcome(eAskCardOutcomeTypes aAskCardOutcomeTypes , AskCardQuery aAskCardOutcome){
			fAskCardOutcomeTypes = aAskCardOutcomeTypes;
			fAskCardQuery = aAskCardOutcome;
		}
		public AskCardQuery getAskCardQuery(){
			return fAskCardQuery;
		}
		public eAskCardOutcomeTypes getAskCardOutcome(){
			return fAskCardOutcomeTypes;
		}
	}
}
