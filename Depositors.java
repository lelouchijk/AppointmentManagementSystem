

public class Depositors {
	private String TokenNumber;
	private String TokenHolderName;
	private String TokenDeperment;
	private java.util.Date Date;

	

	public String getTokenNumber() {
		return TokenNumber;
	}
	public void setTokenNumber(int tokenNumber) {
		TokenNumber = String.valueOf(tokenNumber);
	}
	public String getTokenHolderName() {
		return TokenHolderName;
	}
	public void setTokenHolderName(String tokenHolderName) {
		TokenHolderName = tokenHolderName;
	}
	public String getTokenDeperment() {
		return TokenDeperment;
	}
	public void setTokenDeperment(String tokenDeperment) {
		TokenDeperment = tokenDeperment;
	}

	@Override
	public String toString() {
		return "Customer{customerNo = "+TokenNumber+"customer Name="+TokenHolderName+"customerPhone"+TokenDeperment+"}";
		
	}
	
}
