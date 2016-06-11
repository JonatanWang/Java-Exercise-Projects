package cn.wang.gobang;

/**
 * Enum class of chessman
 * @author wang
 *
 */
public enum Chessman {
	BLACK("●"), WHITE("○");
	private String chessman;
	
	/**
	 * Private constructor
	 * @param chessman
	 */
	private Chessman(String chessman) {
		this.chessman = chessman;
	}
	
	/**
	 * Return String Black or White chess
	 * @return
	 */
	public String getChessman() {
		return this.chessman;
	}
}