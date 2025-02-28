package rocks.zipcode;

/**
 * rocks.zipcode.PayDay
 *
 * input: "Kris", 21.5, 10.0, 0.05
 * output: "Kris 215.00 10.75 204.25”
 *
 * grossPay = hourlyRate * hoursWorked
 * deduction = grossPay * taxRate
 * netPay = grossPay – deduction
 */


public class PayDay {

    // this is an example of how the `pay()` method would be used each week to "run payroll"
    public static void main(String[] args) {
        PayDay payday = new PayDay();

        StringBuilder outputReport = new StringBuilder();
        outputReport.append("PayDayReport for this week\n\n");

        // create an array of TimeCards that have this weeks data in it.
        // each timeCard object contains the data for one person.
        TimeCard[] cards = payday.createRunData();

        for (TimeCard card : cards) {
            String n = card.getName();
            double w = card.getHoursWorked();
            // ... etc for the other 2 fields
            double r = card.getHourlyRate();
            double d = card.getDeductionRate();

            // you need to change the parameters on the method to take the input!
            String result = payday.pay(n, r, w, d);

            outputReport.append(result);
            outputReport.append("\n");
        }

        System.out.println("Email this to the Big Boss:\n\n **PayDay Report**\n\n"+outputReport.toString());
    }


    /**
     * Takes four parameters:
     *   "Kris", 21.5, 10.0, 0.05
     *
     *   Notice the data type of each of the four.
     *   return a string which has the name, the grossPay, deduction, netPay as a
     *   formatted string like this one:
     * @return a string of the form "Kris 215.00 10.75 204.25”
     */
    public String pay(String name, double hourlyRate, double hoursWorked, double taxRate) {

        // these are what i changed
        double gp = this.grossPay(hourlyRate,hoursWorked);
        double dt = this.deductTax(gp, taxRate);
        double np = this.netPay(gp, dt);
        return String.format("%s %.2f %.2f %.2f", name, gp, dt, np);
    }

    // each of these will be USED inside of pay() ^^^^^
    public double grossPay(double rate, double hours) {
        double gp = rate * hours;
        return gp;
    }
    public double deductTax(double gross, double taxRate) {
        double dt = gross * taxRate ;
        return dt;
    }
    public double netPay(double gross, double deduction) {

        double np = gross - deduction;
        return np;
    }

    /*
     * this method need to take a double and produce a formatted dollar figure
     * 0 -> "0.00"
     * 100 -> "100.00"
     * 100.25225 -> "100.25"
     * 33.333333333 -> "33.33"
     *
     * USE this inside of pay() as well!
     */
    public String formatDollars(double amount) {

       // return &.2f, amount;
        return String.format("%.2f", amount);
    }

    /**
     * for the example of how the *pay* method might be used to create a text report
     * this is meant for you to think about how&why pay() is created.
     */
    private class TimeCard {
        private String name;
        private double hoursWorked;
        private double hourlyRate;
        private double deductionRate;

        public TimeCard(String n, double r, double w, double d) {
            this.name = n;
            this.hourlyRate = r;
            this.hoursWorked = w;
            this.deductionRate = d;
        }


        public String getName() {
            return name;
        }

        public double getHoursWorked() {
            return hoursWorked;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }

        public double getDeductionRate() {
            return deductionRate;
        }
    }

    private TimeCard[] createRunData() {
        TimeCard[] cards = {
                new TimeCard("Kris", 21.5, 10.0, 0.05),
                new TimeCard("Dolio", 23.0, 12.0, 0.05),
                new TimeCard("Karen", 22.0, 12.0, 0.05),
        };
        return cards;
    }
}
