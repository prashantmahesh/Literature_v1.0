package litcore.AskCard;

public enum AskCardOutcomeType {
    GaveCard(0),
    CardNotPresent(1);
	private AskCardOutcomeType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	private final int value;
}
