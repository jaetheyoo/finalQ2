package mortgageEstimator.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mortgageEstimator.MortgageEstimator;

public class MortageController {
	private MortgageEstimator main;

	@FXML
	private TextField totalGrossIncome;
	@FXML
	private TextField totalMonthlyDebt;
	@FXML
	private TextField mortageInterestRate;
	@FXML
	private ComboBox<String> term;
	@FXML
	private TextField downPayment;
	@FXML
	private TextField housingPaymentOnly;
	@FXML
	private TextField totalPayments;
	@FXML
	private TextField maxPaymentAllowed;
	@FXML
	private TextField financeAmount;

	private ObservableList<String> terms = FXCollections.observableArrayList(
			"10 years", "15 years", "30 years");

	double calculatedMaxPayment;
	double mortgage;

	@FXML
	public void initialize() {
		term.setItems(terms);
		term.setValue("10 years");
	}

	public void handleUpdates() {
		if (!totalGrossIncome.getText().isEmpty()
				&& !totalMonthlyDebt.getText().isEmpty()
				&& !mortageInterestRate.getText().isEmpty()) {
			double totGrsInc = Double.parseDouble(totalGrossIncome.getText());
			double debt = Double.parseDouble(totalMonthlyDebt.getText());
			int t = getTerm(term.getValue());
			double rate = Double.parseDouble(mortageInterestRate.getText()) / 100;

			calculateMortage(totGrsInc, debt, t, rate);

			financeAmount.setText("$" + mortgage);
			maxPaymentAllowed.setText("$" + calculatedMaxPayment);
		}
	}
	public int getTerm(String term) {
		if (term == "10 years"){
			return 10;
		}
		else if (term == "15 years"){
			return 15;
		}
		else {
			return 30;
		}
	}
	private void calculateMortage(double income, double debt, int trm,
			double rate) {
		calculatePay(income, debt);
		mortgage = Math.round((calculatedMaxPayment
				* (Math.pow(1 + (rate / 12), (trm * 12)) - 1) / (rate / 12))
				/ (Math.pow(1 + (rate / 12), (trm * 12))));
	}

	private void calculatePay(double income, double debt) {
		double housing = ((income / 12) * 0.18);
		housingPaymentOnly.setText("$" + housing);
		double monthly = ((income / 12) * 0.36) - debt;
		totalPayments.setText("$" + monthly + " a month");
		if (housing >= monthly) {
			calculatedMaxPayment = Math.round(monthly);
		} else {
			calculatedMaxPayment = Math.round(housing);
		}
	}

	public void setMainApp(MortgageEstimator main) {
		this.main = main;
	}

	public MortgageEstimator getMain() {
		return main;
	}

}
