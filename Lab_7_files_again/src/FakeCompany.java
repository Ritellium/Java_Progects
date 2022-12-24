public class FakeCompany
{
    String name;
    String shortTitle;
    Integer dateUpdate;
    String address;
    Integer dateFoundation;
    Integer countEmployees;
    String auditor;
    String phone;
    String email;
    String branch;
    String activity;
    String link;

    public FakeCompany(String str, String delimiter)
    {
        String args[] = str.split(delimiter);
        name = args[0];
        shortTitle = args[1];
        dateUpdate = Integer.parseInt(args[2]);
        address = args[3];
        dateFoundation = Integer.parseInt(args[4]);
        countEmployees = Integer.parseInt(args[5]);
        auditor = args[6];
        phone = args[7];
        email = args[8];
        branch = args[9];
        activity = args[10];
        link = args[11];
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder(name);
        str.append("\t" + shortTitle).append("\t" + dateUpdate).append("\t" + address).append("\t" + dateFoundation).append("\t" + countEmployees);
        str.append("\t" + auditor).append("\t" + phone).append("\t" + email).append("\t" + branch).append("\t" + activity).append("\t" + link);
        return str.toString();
    }
}
