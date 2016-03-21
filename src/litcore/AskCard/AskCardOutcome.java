package litcore.AskCard;


public class AskCardOutcome{
	private AskCardOutcomeType outcomeType;
	private AskCardQuery fAskCardQuery;	
	
	public AskCardOutcome(AskCardOutcomeType aAskCardOutcomeType, AskCardQuery aAskCardOutcome){
		outcomeType = aAskCardOutcomeType;
		fAskCardQuery = aAskCardOutcome;
	}
	public AskCardQuery getAskCardQuery(){
		return fAskCardQuery;
	}
	public AskCardOutcomeType getOutcomeType(){
		return outcomeType;
	}
}
