package de.swm.rxjava.tutorial02;

/**
 * Created by xie.fei on 12.04.2016.
 */
public class Avariable {
	private Double varValue = 0.0;
	private String varName = null;



	public Avariable(Double varValue, String varName) {
		this.varValue = varValue;
		this.varName = varName;
	}



	public Double getVarValue() {
		return varValue;
	}



	public void setVarValue(Double varValue) {
		this.varValue = varValue;
	}



	public String getVarName() {
		return varName;
	}



	public void setVarName(String varName) {
		this.varName = varName;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Avariable avariable = (Avariable) o;

		if (varValue != null ? !varValue.equals(avariable.varValue) : avariable.varValue != null)
			return false;
		return !(varName != null ? !varName.equals(avariable.varName) : avariable.varName != null);

	}



	@Override
	public int hashCode() {
		int result = varValue != null ? varValue.hashCode() : 0;
		result = 31 * result + (varName != null ? varName.hashCode() : 0);
		return result;
	}
}
