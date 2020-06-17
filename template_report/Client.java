import java.util.ArrayList;
import java.util.List;

/*
 ** 템플릿메서드 실습 - 1
 */
public class Client {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<Customer>();

        customers.add(new Customer("퐁퐁이", 150));
        customers.add(new Customer("뜽이", 200));
        customers.add(new Customer("잭슨", 100));
        
        // SimpleReport simpleGenerator = new SimpleReport();
        // System.out.println(simpleGenerator.generate(customers));


        ReportGenerator report = new SimpleReport();
        System.out.println(report.generate(customers));

        // System.out.println();
        // ComplexReport complexReport = new ComplexReport();
        // System.out.println(complexReport.generate(customers));
    }
}

class Customer {
    private String name;
    private int point;

    public Customer(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }
}

abstract class ReportGenerator {
    public String generate(List<Customer> customers) {
        List<Customer> selectedCustomers = select(customers);

        String report = getReportHeader(selectedCustomers);

        for (int i = 0; i < selectedCustomers.size(); i++) {
            Customer customer = selectedCustomers.get(i);
            report += getReportBody(customer);
        }

        report += getReportFooter(selectedCustomers);

        // default
        return report;
    }

    // 조건에 부합하는 고객들만 List 에 add 해 반환
    protected List<Customer> select(List<Customer> customers) {
        List<Customer> selected = new ArrayList<Customer>();
        for (Customer customer : customers) {
            if (customerReportCondition(customer)) {

                selected.add(customer);
            }
        }
        return selected;
    }

    protected abstract boolean customerReportCondition(Customer customer);

    protected abstract String getReportHeader(List<Customer> customers);

    protected abstract String getReportBody(Customer customer);

    protected abstract String getReportFooter(List<Customer> customers);
}

class SimpleReport extends ReportGenerator {

    // 조건이 있니 ? 없다면 모두 검사
    @Override
    protected boolean customerReportCondition(Customer customer) {
        return true;
    }

    @Override
    protected String getReportHeader(List<Customer> customers) {
        return String.format("고객 수: %d명 입니다.\n", customers.size());
    }

    @Override
    protected String getReportBody(Customer customer) {
        return String.format("%s : %d\n", customer.getName(), customer.getPoint());
    }

    @Override
    protected String getReportFooter(List<Customer> customers) {
        return "";
    }
}

class ComplexReport extends ReportGenerator {

    @Override
    protected boolean customerReportCondition(Customer customer) {
        return customer.getPoint()>=100;
    }

    @Override
    protected String getReportHeader(List<Customer> customers) {
        return String.format("고객 수: %d명 입니다.\n", customers.size());
    }

    @Override
    protected String getReportBody(Customer customer) {
        return String.format("%d : %s\n", customer.getPoint(), customer.getName());
    }

    @Override
    protected String getReportFooter(List<Customer> customers) {
        int totalPoint = 0;
        for(Customer customer : customers){
            totalPoint += customer.getPoint();
        }
        return String.format("점수 합계 : %d\n", totalPoint);
    }
}